package mx.edu.utez.Integradora_Hotel.service.habitacion;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.habitacion.HabitacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional

public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(habitacionRepository.findAll(),
                HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Habitacion habitacion){
        habitacion = habitacionRepository.saveAndFlush(habitacion);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Habitación registrada"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Habitacion> findById = habitacionRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true,
                    "No se encontró la habitacion"), HttpStatus.NOT_FOUND);
        habitacionRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Habitación encontrada"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Habitacion> findById = habitacionRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se ecnontró el Id"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(habitacionRepository.findById(id),HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> update(Long id, Habitacion updateHabitacion){
        Optional<Habitacion> optionalHabitacion = habitacionRepository.findById(id);
        if (optionalHabitacion.isPresent()){
            Habitacion habitacion = optionalHabitacion.get();
            habitacion.setDescripcion(updateHabitacion.getDescripcion());
            habitacion.setPrecio(updateHabitacion.getPrecio());
            habitacion.setCapacidad(updateHabitacion.getCapacidad());
            habitacion.setCant_camas(updateHabitacion.getCant_camas());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Habitacion Actualizada"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id"), HttpStatus.NOT_FOUND);
        }
    }
}
