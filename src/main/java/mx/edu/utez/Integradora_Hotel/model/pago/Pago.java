package mx.edu.utez.Integradora_Hotel.model.pago;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_Pago;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pago")

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;
    @Column(length = 50, nullable = false)
    private Double monto;
    @Column(length = 50, nullable = false)
    private LocalDateTime fecha_pago;
    @Column(length = 150, nullable = true)
    private Integer num_tarjeta;
    @Column(length = 50, nullable = true)
    private String titular;
    @Column(length = 150, nullable = true)
    private String cvv;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipopago_id")
    private Tipo_Pago tipoPago;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuariopago_id")
    private Usuario usuario;

    @JsonIgnoreProperties(value = {"reserva"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;


    public Pago(Long id_pago, Double monto, LocalDateTime fecha_pago, Integer num_tarjeta, String titular, String cvv, Tipo_Pago tipoPago, Reserva reserva, Usuario usuario) {
        this.id_pago = id_pago;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.num_tarjeta = num_tarjeta;
        this.titular = titular;
        this.cvv = cvv;
        this.tipoPago = tipoPago;
        this.reserva = reserva;
        this.usuario = usuario;
    }
}
