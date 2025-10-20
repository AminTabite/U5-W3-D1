package amintabite.U5_W3_D1.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ViaggioPayload {



    @NotBlank
    @Size(min = 3, max = 20, message = "la destinazione avere almeno 3 caratteri e max 20")
    private  String destinazione;


    private LocalDate dataviaggio;

}
