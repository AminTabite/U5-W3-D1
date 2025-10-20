package amintabite.U5_W3_D1.Services;

import amintabite.U5_W3_D1.Entities.Dipendente;
import amintabite.U5_W3_D1.Exceptions.NotFoundException;
import amintabite.U5_W3_D1.Payloads.DipendentePayload;
import amintabite.U5_W3_D1.Repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DipendenteService {

    @Autowired
    DipendenteRepository dipendenteRepository;

    public Page<Dipendente> FindAll(int pageNumber, int pageSize, String sortBy){
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageSize, pageSize, Sort.by(sortBy));

        return dipendenteRepository.findAll(pageable);
    }


    //salvataggio dipendente

    public Dipendente saveDip(DipendentePayload payload){

        Dipendente newDipendente = new Dipendente(
                payload.getUsername(),
                payload.getNome(),
                payload.getCognome(),
                payload.getEmail()


        );
        Dipendente savedDipendente = dipendenteRepository.save(newDipendente);
        log.info("Autore " + savedDipendente.getCognome() + " salvato correttamente!");
        return savedDipendente;


    }



    // Cerca dipendente

    public Dipendente findById(long iddipendente){

        return dipendenteRepository.findById(iddipendente)
                .orElseThrow(() -> new NotFoundException("dipendente non trovato"));

    }

    //modifica dipendente

    public Dipendente FindByIdAndUpdate(long iddipendente, DipendentePayload payload){

        Dipendente found = dipendenteRepository.findById(iddipendente)
                .orElseThrow(()-> new NotFoundException("modifica fallita"));



        found.setUsername(payload.getUsername());
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());

        return dipendenteRepository.save(found);


    }
    //cancella dipendente

    public void findByIdAndDelete(long iddipendente){

        Dipendente found = dipendenteRepository.findById(iddipendente)
                .orElseThrow(()-> new NotFoundException("eliminazione fallita"));
        dipendenteRepository.delete(found);

        log.info("dipendente con ID " + iddipendente + " eliminato correttamente.");




    }


}
