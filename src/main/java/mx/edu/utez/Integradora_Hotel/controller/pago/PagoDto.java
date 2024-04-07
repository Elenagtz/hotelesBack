package mx.edu.utez.Integradora_Hotel.controller.pago;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_Pago;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

public class PagoDto {

    private Long id_pago;
    private Double monto;
    private LocalDateTime fecha_pago;
    private Integer num_tarjeta;
    private String titular;
    private String cvv;
    private Tipo_Pago tipoPago;
    private Reserva reserva;
    private Usuario usuario;

    public Pago toEntity(){
        return new Pago(id_pago, monto, fecha_pago, num_tarjeta, titular, cvv, tipoPago, reserva, usuario);
    }

}
