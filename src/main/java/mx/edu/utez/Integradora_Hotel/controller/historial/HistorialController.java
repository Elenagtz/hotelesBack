package mx.edu.utez.Integradora_Hotel.controller.historial;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.controller.elemento.ElementoDto;
import mx.edu.utez.Integradora_Hotel.service.elemento.ElementoService;
import mx.edu.utez.Integradora_Hotel.service.historial.HistorialService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/historial")
public class HistorialController {
    private  final HistorialService historialService;
    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return historialService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody HistorialDto historialDto) {
        return historialService.register(historialDto.toEntity());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return historialService.findById(id);
    }
}
