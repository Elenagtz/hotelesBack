package mx.edu.utez.Integradora_Hotel.controller.tipo_pago;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.service.tipo_pago.Tipo_pagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/tipopago")

public class Tipo_PagoController {
    private final Tipo_pagoService tipoPagoService;

    public Tipo_PagoController(Tipo_pagoService tipoPagoService) {
        this.tipoPagoService = tipoPagoService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return tipoPagoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return tipoPagoService.findById(id);

    }
}
