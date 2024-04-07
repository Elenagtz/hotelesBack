package mx.edu.utez.Integradora_Hotel.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.historial.Historial;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.role.Role;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name =  "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 50, nullable = false)
    private String apellidoP;
    @Column(length = 50, nullable = false)
    private String apellidoM;
    @Column(length = 30, nullable = false)
    private String correo;
    @Column(length = 150, nullable = false)
    private String contrasena;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status; //no

    //RELACIONES
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties(value = {"usuario"})
    private Role role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Pago> pago;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuarios")
    private List<Reserva> reserva;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_historial", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "historial_id"))
    private List<Historial> historial;


    public Usuario(Long id_usuario, String nombre, String apellidoP, String apellidoM, String correo, String contrasena, Boolean status) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.contrasena = contrasena;
        this.status = status;
    }

    public Usuario(Long id_usuario, String nombre, String apellidoP, String apellidoM, String correo, String contrasena, Boolean status, Role role) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.contrasena = contrasena;
        this.status = status;
        this.role = role;
    }

    public Usuario(String correo, String contrasena, Role role) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.role = role;
    }

    public Usuario( String nombre, String apellidoP, String apellidoM, String correo, String contrasena,  Boolean status) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.contrasena = contrasena;
        this.status = status;
    }
}
