package mx.edu.utez.Integradora_Hotel.controller.paquete;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class PaqueteDto {
    private Long id_paquete;
    private String nombre_paquete;
    private Double precio;
    private String descripcion;
    private List<Reserva> reservas;
    private Elemento elemento;
    private String imagen_paquete;

    public Paquete toEntity(){
        return new Paquete(id_paquete, nombre_paquete, precio, descripcion, reservas, elemento, imagen_paquete);
    }

}
