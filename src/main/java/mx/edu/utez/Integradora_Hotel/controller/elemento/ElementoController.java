package mx.edu.utez.Integradora_Hotel.controller.elemento;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.controller.paquete.PaqueteDto;
import mx.edu.utez.Integradora_Hotel.service.elemento.ElementoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/elemento")

public class ElementoController {

    private final ElementoService elementoService;

    public ElementoController(ElementoService elementoService) {
        this.elementoService = elementoService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll() {
        return elementoService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody ElementoDto elementoDto) {
        return elementoService.register(elementoDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return elementoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        return elementoService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody ElementoDto elementoDto) {
        ResponseEntity<ApiResponse> updateResponse = elementoService.update(id, elementoDto.toEntity());
        return updateResponse;
    }
}
