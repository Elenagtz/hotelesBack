package mx.edu.utez.Integradora_Hotel.model.elemento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.categoria.Categoria;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "elemento")

public class Elemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    @Column(length = 50, nullable = false)
    private String nombre_producto;
    @Column(length = 50, nullable = false)
    private Double precio;
    @Column(length = 150, nullable = true)
    private String descripcion;
    @Column(columnDefinition = "TEXT")
    private String imagen_elemento;

    @ManyToMany(mappedBy = "elementos")
    private List<Reserva> reservas;

    @JsonIgnoreProperties(value = {"paquetes"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elemento")
    private List<Paquete> paquetes;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Elemento(Long id_producto, String nombre_producto, Double precio, String descripcion) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Elemento(Long id_producto, String nombre_producto, Double precio, String descripcion, Categoria categoria, String imagen_elemento) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagen_elemento = imagen_elemento;
    }
}
