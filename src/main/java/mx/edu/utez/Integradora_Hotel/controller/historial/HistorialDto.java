package mx.edu.utez.Integradora_Hotel.controller.historial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.historial.Historial;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class HistorialDto {

    private Long id_historial;
    private Double subtotal;
    private Double impuestos;
    private Double total;
    private LocalDateTime fecha_reserva;



    public Historial toEntity(){
        return new Historial(id_historial, subtotal, impuestos, total, fecha_reserva);
    }
}
