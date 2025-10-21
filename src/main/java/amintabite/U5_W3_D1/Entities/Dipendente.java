package amintabite.U5_W3_D1.Entities;

import amintabite.U5_W3_D1.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Dipendente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iddipendente;
    private String username;
    private String nome;
    private String cognome;
    private  String email;
    private String password;
    private Roles role;

    @OneToMany
    @JoinColumn(name = "Dipendente_id")
    private List<Prenotazione> prenotazioni;


    public Dipendente(String username ,String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }




}
