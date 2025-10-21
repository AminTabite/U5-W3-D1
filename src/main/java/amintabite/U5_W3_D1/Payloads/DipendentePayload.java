package amintabite.U5_W3_D1.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class DipendentePayload {

    @NotBlank
    @Size(min = 3, max = 20, message = "nome e cognome devono avere almeno 3 caratteri e max 20")
    private String username;

    @NotBlank
    @Size(min = 3, max = 20, message = "nome e cognome devono avere almeno 3 caratteri e max 20")
    private String nome;
    @NotBlank
    @Size(min = 3, max = 20, message = "nome e cognome devono avere almeno 3 caratteri e max 20")
    private String cognome;
    @NotBlank
    @Email(message = "L'indirizzo email inserito non è nel formato corretto!")
    private  String email;

    private String password;

}
