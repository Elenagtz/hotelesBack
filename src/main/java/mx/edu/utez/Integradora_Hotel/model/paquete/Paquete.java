package mx.edu.utez.Integradora_Hotel.model.paquete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "paquete")

public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paquete;
    @Column(length = 50, nullable = false)
    private String nombre_paquete;
    @Column(length = 50, nullable = false)
    private Double precio;
    @Column(length = 150, nullable = false)
    private String descripcion;
    @Column(columnDefinition = "TEXT")
    private String imagen_paquete;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquete")
    private List<Reserva> reservas;

    @JsonIgnoreProperties(value = {"paquetes"})
    @ManyToOne
    @JoinColumn(name = "elementos_id", nullable = true)
    private Elemento elemento;

    public Paquete(Long id_paquete, String nombre_paquete, Double precio, String descripcion) {
        this.id_paquete = id_paquete;
        this.nombre_paquete = nombre_paquete;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Paquete(Long id_paquete, String nombre_paquete, Double precio, String descripcion,List<Reserva> reservas, Elemento elemento, String imagen_paquete) {
        this.id_paquete = id_paquete;
        this.nombre_paquete = nombre_paquete;
        this.precio = precio;
        this.descripcion = descripcion;
        this.reservas = reservas;
        this.elemento = elemento;
        this.imagen_paquete = imagen_paquete;
    }
}
