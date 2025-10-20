package amintabite.U5_W3_D1.Controllers;



import amintabite.U5_W3_D1.Entities.Prenotazione;

import amintabite.U5_W3_D1.Payloads.PrenotazionePayload;
import amintabite.U5_W3_D1.Services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")

public class PrenotazioneController {

@Autowired
    private PrenotazioneService prenotazioneService;



@GetMapping

public Page<Prenotazione> getPrenotazioni(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue ="4" ) int size,
        @RequestParam(defaultValue = "datarichiesta") String sortBy)
{
    return prenotazioneService.FindAll(page, size, sortBy);
}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody PrenotazionePayload body){

        return prenotazioneService.savePrenotazione(body);
    }

    @GetMapping("/{idprenotazione}")

    public Prenotazione getPrenotazioneById(@PathVariable long idprenotazione){
        return prenotazioneService.findById(idprenotazione);
    }

    @DeleteMapping("/{idprenotazione}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable long idprenotazione){
        prenotazioneService.findByIdAndDelete(idprenotazione);

    }





}
