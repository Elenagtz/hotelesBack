package mx.edu.utez.Integradora_Hotel.controller.tipo_habitacion;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacion;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class Tipo_habitacionDto {

    private Long id_tipohab;
    private String nombrehabitacion;
    private List<Habitacion> habitacions;

    public Tipo_habitacion toEntity(){
        return new Tipo_habitacion(id_tipohab, nombrehabitacion, habitacions);
    }

}
