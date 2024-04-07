package mx.edu.utez.Integradora_Hotel.service.historial;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.historial.Historial;
import mx.edu.utez.Integradora_Hotel.model.historial.HistorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class HistorialService {
    private final HistorialRepository historialRepository;
    public HistorialService(HistorialRepository historialRepository){this.historialRepository=historialRepository;}
@Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(historialRepository.findAll(), HttpStatus.OK),HttpStatus.OK);
}
@Transactional(rollbackFor = {SQLException.class})
public  ResponseEntity<ApiResponse>register(Historial historial){
        historial = historialRepository.saveAndFlush(historial);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,false, "Historial Registrado"), HttpStatus.OK);

}

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Historial> findById = historialRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id"), HttpStatus.NOT_FOUND);
        historialRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Usuario encontrada"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Historial> findById = historialRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(historialRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

}
