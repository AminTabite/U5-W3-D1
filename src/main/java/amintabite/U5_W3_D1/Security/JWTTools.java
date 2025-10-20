package amintabite.U5_W3_D1.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

}
