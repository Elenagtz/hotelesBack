package mx.edu.utez.Integradora_Hotel.controller.reserva;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.service.reserva.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/reserva")

public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return reservaService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody ReservaDto reservaDto) {
        return reservaService.register(reservaDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return reservaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        return reservaService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody ReservaDto reservaDto) {
        ResponseEntity<ApiResponse> updateResponse = reservaService.update(id, reservaDto.toEntity());
        return updateResponse;
    }
}