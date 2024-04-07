package mx.edu.utez.Integradora_Hotel.model.tipo_pago;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Tipo_PagoRepository extends JpaRepository<Tipo_Pago, Long> {
    Optional<Tipo_Pago> findByNombre(String nombre_pago);

}
