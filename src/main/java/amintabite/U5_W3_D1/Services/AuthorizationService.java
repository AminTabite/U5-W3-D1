package amintabite.U5_W3_D1.Services;


import amintabite.U5_W3_D1.Entities.Dipendente;
import amintabite.U5_W3_D1.Exceptions.UnauthorizedException;
import amintabite.U5_W3_D1.Payloads.LoginPayload;
import amintabite.U5_W3_D1.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;


    public String CheckCredentialAndDoToken(LoginPayload body){


        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if(found.getPassword().equals(body.password())) {
            return jwtTools.createToken(found);

        }  else {

            throw new UnauthorizedException("credenziali errate");
        }



    }












}
