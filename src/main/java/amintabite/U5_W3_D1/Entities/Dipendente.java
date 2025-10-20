package amintabite.U5_W3_D1.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iddipendente;
    private String username;
    private String nome;
    private String cognome;
    private  String email;
    @OneToMany
    @JoinColumn(name = "Dipendente_id")
    private List<Prenotazione> prenotazioni;


    public Dipendente(String username ,String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
