package mx.edu.utez.Integradora_Hotel.controller.paquete;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.controller.reserva.ReservaDto;
import mx.edu.utez.Integradora_Hotel.service.paquete.PaqueteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/paquete")

public class PaqueteController {

    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return paqueteService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody PaqueteDto paqueteDto) {
        return paqueteService.register(paqueteDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return paqueteService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        return paqueteService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody PaqueteDto paqueteDto) {
        ResponseEntity<ApiResponse> updateResponse = paqueteService.update(id, paqueteDto.toEntity());
        return updateResponse;
    }
}


