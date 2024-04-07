package mx.edu.utez.Integradora_Hotel.model.habitacion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacion;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "habitacion")

public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_habitacion;
    @Column(length = 2, nullable = false)
    private Integer num_habitacion;
    @Column(length = 50, nullable = false)
    private String capacidad;
    @Column(length = 50, nullable = false)
    private String cant_camas;
    @Column(length = 50, nullable = false)
    private Double precio;
    @Column(length = 150, nullable = false)
    private String descripcion;
    @Column(columnDefinition = "TEXT")
    private String imagen_hab;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipohab_id")
    private Tipo_habitacion tipoHabitacion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "habitacion_reserva", joinColumns = @JoinColumn(name = "habitacion_id"),
    inverseJoinColumns = @JoinColumn(name = "reservas_id"))
    private List<Reserva> reservas;

    public Habitacion(Long id_habitacion, Integer num_habitacion, String capacidad, String cant_camas, Double precio, String descripcion, Tipo_habitacion tipoHabitacion, String imagen_hab) {
        this.id_habitacion = id_habitacion;
        this.num_habitacion = num_habitacion;
        this.capacidad = capacidad;
        this.cant_camas = cant_camas;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipoHabitacion = tipoHabitacion;
        this.imagen_hab = imagen_hab;
    }
}
