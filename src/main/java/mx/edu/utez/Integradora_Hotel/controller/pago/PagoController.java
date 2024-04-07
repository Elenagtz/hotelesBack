package mx.edu.utez.Integradora_Hotel.controller.pago;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.service.pago.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/pago")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return pagoService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody PagoDto pagoDto){
        return pagoService.register(pagoDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return pagoService.findById(id);
    }
}
