package amintabite.U5_W3_D1.Services;

import amintabite.U5_W3_D1.Entities.Dipendente;
import amintabite.U5_W3_D1.Entities.Prenotazione;
import amintabite.U5_W3_D1.Entities.Viaggio;
import amintabite.U5_W3_D1.Exceptions.NotFoundException;
import amintabite.U5_W3_D1.Payloads.PrenotazionePayload;
import amintabite.U5_W3_D1.Repositories.DipendenteRepository;
import amintabite.U5_W3_D1.Repositories.PrenotazioneRepository;
import amintabite.U5_W3_D1.Repositories.ViaggioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioneService {
    @Autowired
    DipendenteRepository dipendenteRepository;

    @Autowired
    ViaggioRepository viaggioRepository;

    @Autowired
    PrenotazioneRepository prenotazioneRepository;



    public Page<Prenotazione> FindAll(int pageNumber, int pageSize, String sortBy){
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageSize, pageSize, Sort.by(sortBy));

        return prenotazioneRepository.findAll(pageable);
    }




    //salvataggio prenotazione

    public Prenotazione savePrenotazione(PrenotazionePayload payload) {

        Dipendente dip = dipendenteRepository.findById(payload.getDipendenteId())
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(payload.getViaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        Prenotazione pren = new Prenotazione(payload.getDatarichiesta(), payload.getNoteextra());
        pren.setDipendente(dip);
        pren.setViaggio(viaggio);

        return prenotazioneRepository.save(pren);
    }

    // Cerca prenotazione

    public  Prenotazione findById(long idprenotazione){


        return prenotazioneRepository.findById(idprenotazione)
                .orElseThrow(() -> new NotFoundException("dipendente non trovato"));
    }


    // modifica prenotazione

    public Prenotazione FindByIdAndUpdate(long idprenotazione, PrenotazionePayload payload){


        Prenotazione found = prenotazioneRepository.findById(idprenotazione)
                .orElseThrow(() -> new NotFoundException("prenotazione non trovata"));

        Dipendente dip = dipendenteRepository.findById(payload.getDipendenteId())
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(payload.getViaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));


        found.setDatarichiesta(payload.getDatarichiesta());
        found.setNoteextra(payload.getNoteextra());
        found.setDipendente(dip);
        found.setViaggio(viaggio);

        return found;

    }


    // cancella prenotazione


    public void findByIdAndDelete(long idprenotazione) {

        Prenotazione found = prenotazioneRepository.findById(idprenotazione)
                .orElseThrow(() -> new NotFoundException("errore eliminazione"));

        log.info("Prenotazione con ID " + idprenotazione + " eliminato correttamente.");


    }
}
