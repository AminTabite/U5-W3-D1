package amintabite.U5_W3_D1.Security;


import amintabite.U5_W3_D1.Entities.Dipendente;
import amintabite.U5_W3_D1.Exceptions.UnauthorizedException;
import amintabite.U5_W3_D1.Services.DipendenteService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTfilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {

        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer "))
        throw new UnauthorizedException("Inserire il token nell'authorization header nel formato giusto!");



        String accessToken= authHeader.replace("Bearer ", "");

        jwtTools.verifyToken(accessToken);


        // 1. Cerchiamo l'utente nel db (l'id sta nel token!)
        // 1.1 Estraiamo l'id dal token
        long Iddipendente = jwtTools.extractIdFromToken(accessToken);
        // 1.2 findById
        Dipendente found = dipendenteService.findById(Iddipendente);



        Authentication authentication = new UsernamePasswordAuthenticationToken(found, null, found.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);


    }



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }


}
