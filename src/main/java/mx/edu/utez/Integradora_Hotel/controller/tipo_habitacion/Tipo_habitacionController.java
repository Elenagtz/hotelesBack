package mx.edu.utez.Integradora_Hotel.controller.tipo_habitacion;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.service.tipo_habitacion.Tipo_habitacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/tipohab")

public class Tipo_habitacionController {

    private final Tipo_habitacionService tipoHabitacionService;

    public Tipo_habitacionController(Tipo_habitacionService tipoHabitacionService) {
        this.tipoHabitacionService = tipoHabitacionService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return tipoHabitacionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        return tipoHabitacionService.findById(id);
    }
}
