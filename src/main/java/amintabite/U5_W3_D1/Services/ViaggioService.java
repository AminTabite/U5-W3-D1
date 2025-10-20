package amintabite.U5_W3_D1.Services;

import amintabite.U5_W3_D1.Entities.Viaggio;
import amintabite.U5_W3_D1.Exceptions.NotFoundException;
import amintabite.U5_W3_D1.Payloads.ViaggioPayload;
import amintabite.U5_W3_D1.Repositories.ViaggioRepository;
import amintabite.U5_W3_D1.StatoViaggio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ViaggioService {

    @Autowired
    ViaggioRepository viaggioRepository;

    public Page<Viaggio> FindAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 10) pageSize = 10;
        Pageable pageable = PageRequest.of(pageSize, pageSize, Sort.by(sortBy));

        return viaggioRepository.findAll(pageable);
    }


    //salvataggio viaggio

    public Viaggio saveViaggio(ViaggioPayload payload) {
        Viaggio newViaggio = new Viaggio(
                payload.getDestinazione(),
                payload.getDataviaggio()
        );
        newViaggio.setStatoViaggio(StatoViaggio.IN_PROGRAMMA); // default iniziale
        Viaggio savedViaggio = viaggioRepository.save(newViaggio);
        log.info("Viaggio verso " + savedViaggio.getDestinazione() + " salvato correttamente!");
        return savedViaggio;
    }

    // Cerca viaggio

        public Viaggio findById ( long idviaggio){

            return viaggioRepository.findById(idviaggio)
                    .orElseThrow(() -> new NotFoundException("viaggio non trovato"));

        }

        //modifica viaggio

        public Viaggio FindByIdAndUpdate(long idviaggio, ViaggioPayload payload){

            Viaggio found = viaggioRepository.findById(idviaggio)
                    .orElseThrow(() -> new NotFoundException("modifica fallita"));



                found.setDestinazione(payload.getDestinazione());
                found.setDataviaggio(payload.getDataviaggio());


                Viaggio updatedViaggio = viaggioRepository.save(found);
                log.info("Viaggio con ID " + idviaggio + " aggiornato correttamente!");

            return viaggioRepository.save(found);


        }
        //cancella viaggio

        public void findByIdAndDelete( long idviaggio){

            Viaggio found = viaggioRepository.findById(idviaggio)
                    .orElseThrow(() -> new NotFoundException("eliminazione fallita"));
            viaggioRepository.delete(found);

            log.info("dipendente con ID " + idviaggio + " eliminato correttamente.");


        }
    }
