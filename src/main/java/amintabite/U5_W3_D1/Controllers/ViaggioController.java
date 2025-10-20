package amintabite.U5_W3_D1.Controllers;

import amintabite.U5_W3_D1.Entities.Viaggio;
import amintabite.U5_W3_D1.Payloads.ViaggioPayload;
import amintabite.U5_W3_D1.Services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;
    @GetMapping
    public Page<Viaggio> getViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "destinazione") String sortBy) {
        return viaggioService.FindAll(page, size, sortBy);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio createViaggio(@RequestBody ViaggioPayload body){

        return viaggioService.saveViaggio(body);
    }


    @GetMapping("/{idviaggio}")

    public Viaggio getDipendenteById(@PathVariable long idviaggio){
        return viaggioService.findById(idviaggio);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable long idviaggio){
        viaggioService.findByIdAndDelete(idviaggio);

    }




}
