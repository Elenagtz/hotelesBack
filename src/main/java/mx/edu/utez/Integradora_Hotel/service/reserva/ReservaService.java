package mx.edu.utez.Integradora_Hotel.service.reserva;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.reserva.ReservaRepository;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(reservaRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Reserva reserva){
        reserva = reservaRepository.saveAndFlush(reserva);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Reserva registrado"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Reserva> findById = reservaRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id"), HttpStatus.NOT_FOUND);
        reservaRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Usuario encontrada"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Reserva> findById = reservaRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(reservaRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id, Reserva updateReserva){
        Optional<Reserva> optionalReserva = reservaRepository.findById(id);
        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reserva.setEstado_reserva(updateReserva.getEstado_reserva());
            reserva.setFecha_entrada(updateReserva.getFecha_entrada());
            reserva.setFecha_salida(updateReserva.getFecha_salida());
            reserva.setTotal(updateReserva.getTotal());
            reserva.setEstado_reserva(updateReserva.getEstado_reserva());
            reserva.setElementos(updateReserva.getElementos());

            reservaRepository.save(reserva);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Reserva actualizado"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

}
