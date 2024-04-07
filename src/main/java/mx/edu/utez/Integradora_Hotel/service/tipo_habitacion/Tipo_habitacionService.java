package mx.edu.utez.Integradora_Hotel.service.tipo_habitacion;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacion;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@Transactional

public class Tipo_habitacionService {

    private final Tipo_habitacionRepository tipoHabitacionRepository;

    public Tipo_habitacionService(Tipo_habitacionRepository tipoHabitacionRepository) {
        this.tipoHabitacionRepository = tipoHabitacionRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(tipoHabitacionRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Tipo_habitacion> findById =tipoHabitacionRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "N se encontró el Id"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(tipoHabitacionRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }
}
