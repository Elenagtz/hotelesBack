package mx.edu.utez.Integradora_Hotel.model.tipo_habitacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Tipo_habitacionRepository extends JpaRepository<Tipo_habitacion, Long> {
    Optional<Tipo_habitacion> findByNombrehabitacion(String nombrehabitacion);

}
