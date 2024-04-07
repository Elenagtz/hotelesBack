package mx.edu.utez.Integradora_Hotel.service.elemento;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.elemento.ElementoRepository;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional

public class ElementoService {

    private final ElementoRepository elementoRepository;

    public ElementoService(ElementoRepository elementoRepository) {
        this.elementoRepository = elementoRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(elementoRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Elemento elemento){
        elemento = elementoRepository.saveAndFlush(elemento);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Elemento registrado"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        Optional<Elemento> findById = elementoRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id"), HttpStatus.NOT_FOUND);
        elementoRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Usuario encontrada"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        Optional<Elemento> findById = elementoRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(elementoRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id, Elemento updateElemento){
        Optional<Elemento> optionalElemento = elementoRepository.findById(id);
        if (optionalElemento.isPresent()) {
            Elemento elemento = optionalElemento.get();
            elemento.setNombre_producto(updateElemento.getNombre_producto());
            elemento.setDescripcion(updateElemento.getDescripcion());
            elemento.setPrecio(updateElemento.getPrecio());
            elemento.setPaquetes(updateElemento.getPaquetes());
            elemento.setCategoria(updateElemento.getCategoria());
            elemento.setReservas(updateElemento.getReservas());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Reserva actualizado"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

}
