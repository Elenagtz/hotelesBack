package mx.edu.utez.Integradora_Hotel.controller.elemento;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.categoria.Categoria;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class ElementoDto {
    private Long id_producto;
    private String nombre_producto;
    private Double precio;
    private String descripcion;
    private Categoria categoria;
    private String imagen_elemento;

    public Elemento toEntity(){
        return new Elemento(id_producto, nombre_producto,precio,descripcion,categoria, imagen_elemento);
    }
}
