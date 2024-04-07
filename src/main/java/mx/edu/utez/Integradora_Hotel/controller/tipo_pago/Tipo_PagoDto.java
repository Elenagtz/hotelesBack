package mx.edu.utez.Integradora_Hotel.controller.tipo_pago;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_Pago;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class Tipo_PagoDto {

    private Long id_tipopago;
    private String nombre;
    private List<Pago> pagos;

    public Tipo_Pago toEntity(){
        return new Tipo_Pago(id_tipopago, nombre, pagos);
    }

}
