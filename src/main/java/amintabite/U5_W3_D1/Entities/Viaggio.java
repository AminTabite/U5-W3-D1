package amintabite.U5_W3_D1.Entities;

import amintabite.U5_W3_D1.StatoViaggio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idviaggio;

    private String destinazione;

    private LocalDate dataviaggio;
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;
    @OneToMany
    @JoinColumn(name = "Viaggio_id")
    private List<Prenotazione> prenotazioni;





    public Viaggio( String destinazione,LocalDate dataviaggio) {

        this.destinazione = destinazione;
        this.dataviaggio = dataviaggio;
        this.statoViaggio = statoViaggio;

    }
}
