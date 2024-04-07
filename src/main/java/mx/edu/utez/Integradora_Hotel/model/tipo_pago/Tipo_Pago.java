package mx.edu.utez.Integradora_Hotel.model.tipo_pago;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tipo_pago")

public class Tipo_Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipopago;
    @Column(length = 50, nullable = false)
    private String nombre;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tipoPago")
    private List<Pago> pagos;

    public Tipo_Pago(Long id_tipopago, String nombre, List<Pago> pagos) {
        this.id_tipopago = id_tipopago;
        this.nombre  = nombre;
        this.pagos = pagos;
    }
}
