package mx.edu.utez.Integradora_Hotel.service.tipo_pago;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_Pago;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_PagoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@Transactional

public class Tipo_pagoService {

    private final Tipo_PagoRepository tipoPagoRepository;

    public Tipo_pagoService(Tipo_PagoRepository tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(tipoPagoRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Tipo_Pago> findById = tipoPagoRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(tipoPagoRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }
    }

