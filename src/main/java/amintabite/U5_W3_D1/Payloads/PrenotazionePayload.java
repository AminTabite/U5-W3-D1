package amintabite.U5_W3_D1.Payloads;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class PrenotazionePayload {

@FutureOrPresent(message = " la data deve essere odierna o nel futuro")
    private LocalDate datarichiesta;

    private String noteextra;

    private long dipendenteId;

    private long viaggioId;



    public PrenotazionePayload(LocalDate datarichiesta, String noteextra) {
        this.datarichiesta = datarichiesta;
        this.noteextra = noteextra;
    }
}
