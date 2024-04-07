package mx.edu.utez.Integradora_Hotel.controller.reserva;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class ReservaDto {
    private Long id_reserva;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private Double total;
    private String estado_reserva;
    private Usuario usuario;
    private List<Pago> pago;
    private Paquete paquete;

    public Reserva toEntity(){
        return new Reserva(id_reserva, fecha_entrada, fecha_salida, total, estado_reserva, usuario, paquete, pago);
    }
}
