package mx.edu.utez.Integradora_Hotel.model.historial;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "historial")
public class Historial {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id_historial;
    @Column(length = 50, nullable = false)
    private Double subtotal;
    @Column(length = 50, nullable = false)
    private Double impuestos;
    @Column(length = 50, nullable = false)
    private Double total;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_reserva;

    @ManyToMany(mappedBy = "historial")
    private List<Usuario> usuario;

    public Historial(Long id_historial, Double subtotal, Double impuestos, Double total, LocalDateTime fecha_reserva) {
        this.id_historial = id_historial;
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
        this.fecha_reserva = fecha_reserva;
    }
}
