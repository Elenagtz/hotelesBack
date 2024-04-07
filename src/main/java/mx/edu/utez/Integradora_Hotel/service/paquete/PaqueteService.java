package mx.edu.utez.Integradora_Hotel.service.paquete;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.paquete.PaqueteRepository;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional

public class PaqueteService {
    private final PaqueteRepository paqueteRepository;

    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }


    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(paqueteRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Paquete paquete){
        paquete = paqueteRepository.saveAndFlush(paquete);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Paquete registrado"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Paquete> findById = paqueteRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id"), HttpStatus.NOT_FOUND);
        paqueteRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Paquete encontrado"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Paquete> findById = paqueteRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(paqueteRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id, Paquete updatePaquete){
        Optional<Paquete> optionalPaquete = paqueteRepository.findById(id);
        if (optionalPaquete.isPresent()) {
            Paquete paquete = optionalPaquete.get();
            paquete.setNombre_paquete(updatePaquete.getNombre_paquete());
            paquete.setPrecio(updatePaquete.getPrecio());
            paquete.setDescripcion(updatePaquete.getDescripcion());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Paquete actualizado"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

}

