package amintabite.U5_W3_D1.Controllers;

import amintabite.U5_W3_D1.Entities.Dipendente;
import amintabite.U5_W3_D1.Payloads.DipendentePayload;
import amintabite.U5_W3_D1.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;


    @GetMapping
    @PreAuthorize("hasAuthority('RESPONSABILE')")
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue ="4" ) int size,
            @RequestParam(defaultValue = "nome") String sortBy)
            {
    return dipendenteService.FindAll(page, size, sortBy);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('RESPONSABILE')")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody DipendentePayload body){




                return dipendenteService.saveDip(body);
    }


        @GetMapping("/{iddipendente}")

     public Dipendente getDipendenteById(@PathVariable long iddipendente){
                return dipendenteService.findById(iddipendente);
        }


    @DeleteMapping("/{iddipendente}")
    @PreAuthorize("hasAuthority('RESPONSABILE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable long iddipendente){
                dipendenteService.findByIdAndDelete(iddipendente);

    }




}
