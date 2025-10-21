package amintabite.U5_W3_D1.Controllers;

import amintabite.U5_W3_D1.Entities.Dipendente;
import amintabite.U5_W3_D1.Exceptions.ExceptionValidation;
import amintabite.U5_W3_D1.Payloads.DipendentePayload;
import amintabite.U5_W3_D1.Payloads.LoginPayload;
import amintabite.U5_W3_D1.Payloads.tokenpayload;
import amintabite.U5_W3_D1.Services.AuthorizationService;
import amintabite.U5_W3_D1.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AutorizzazioneController {

        @Autowired
        private AuthorizationService authService;

        @Autowired
        private DipendenteService dipendenteService;

        @PostMapping("/login")
        public tokenpayload login(@RequestBody LoginPayload body) {
            return new tokenpayload(authService.CheckCredentialAndDoToken(body));
        }

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public Dipendente createDipendente(@RequestBody @Validated DipendentePayload payload, BindingResult validationResult) {
            // @Validated serve per "attivare" la validazione
            // BindingResult è un oggetto che contiene tutti gli errori e anche dei metodi comodi da usare tipo .hasErrors()
            if (validationResult.hasErrors()) {

                throw new ExceptionValidation(validationResult.getFieldErrors()
                        .stream().map(fieldError -> fieldError.getDefaultMessage()).toList().toString());
            }
            return this.dipendenteService.saveDip(payload);
        }

    }
