package mx.edu.utez.Integradora_Hotel.model.reserva;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_entrada;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_salida;
    @Column(length = 50, nullable = false)
    private Double total;
    @Column(length = 50, nullable = false)
    private String estado_reserva;


    @ManyToOne
    @JoinColumn(name = "usuarioreserva_id")
    private Usuario usuarios;


    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = true)
    private Paquete paquete;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reserva_elemento", joinColumns = @JoinColumn(name = "reservas_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_id"))
    private List<Elemento> elementos;

    @ManyToMany(mappedBy = "reservas")
    private List<Habitacion> habitaciones;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "reserva")
    private List<Pago> pagos;


    public Reserva(Long id_reserva, LocalDateTime fecha_entrada, LocalDateTime fecha_salida, Double total, String estado_reserva, Usuario usuarios, Paquete paquete, List<Pago> pagos) {
        this.id_reserva = id_reserva;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.total = total;
        this.estado_reserva = estado_reserva;
        this.usuarios = usuarios;
        this.paquete = paquete;
        this.pagos = pagos;
    }
}

