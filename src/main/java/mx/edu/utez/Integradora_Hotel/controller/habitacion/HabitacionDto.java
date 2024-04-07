package mx.edu.utez.Integradora_Hotel.controller.habitacion;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacion;

@NoArgsConstructor
@Getter
@Setter

public class HabitacionDto {

    private Long id_habitacion;
    private Integer num_habitacion;
    private String capacidad;
    private String cant_camas;
    private Double precio;
    private String descripcion;
    private Tipo_habitacion tipoHabitacion;
    private String imagen_hab;

    public Habitacion toEntity(){
        return new Habitacion(id_habitacion, num_habitacion, capacidad,cant_camas, precio, descripcion, tipoHabitacion, imagen_hab);
    }
}
