package mx.edu.utez.Integradora_Hotel.config;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.Integradora_Hotel.model.categoria.Categoria;
import mx.edu.utez.Integradora_Hotel.model.categoria.CategoriaRepository;
import mx.edu.utez.Integradora_Hotel.model.role.Role;
import mx.edu.utez.Integradora_Hotel.model.role.RoleRepository;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacion;
import mx.edu.utez.Integradora_Hotel.model.tipo_habitacion.Tipo_habitacionRepository;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_Pago;
import mx.edu.utez.Integradora_Hotel.model.tipo_pago.Tipo_PagoRepository;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import mx.edu.utez.Integradora_Hotel.model.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor

public class InitialConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final Tipo_PagoRepository tipoPagoRepository;
    private final Tipo_habitacionRepository tipoHabitacionRepository;
    private final CategoriaRepository categoriaRepository;


    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run (String... args) throws Exception{
        Role adminRole = getOrSaveRole(new Role("GERENTE_ROLE"));
        getOrSaveRole(new Role("RECEPCIONISTA_ROLE"));
        getOrSaveRole(new Role("CLIENTE_ROLE"));

        // Creación de usuario
        Usuario user = getOrSaveUser(
                new Usuario("Lizbeth", "Santibañez", "Cruz", "lizz@gmail.com", encoder.encode("Lizz"), true)
        );
        // Asignarle Rol de Admin :O
        saveUserRoles(user.getId_usuario(), adminRole.getId_role());

        // Creación de tipos de pago
        createTipoPago("Efectivo");
        createTipoPago("Tarjeta");

        // Creación de tipo de habitación uwu
        createTipoHabitacion("Sencilla");
        createTipoHabitacion("Junior Suite");
        createTipoHabitacion("Senior Suite");
        createTipoHabitacion("Master Suite");

        // Creación de las categorías :)
        createCategoria("Spa");
        createCategoria("Restaurante");
        createCategoria("Miscelaneos");
    }

    @Transactional
    public Role getOrSaveRole(Role role) {
        Optional<Role> foundRole = roleRepository.findByNombre(role.getNombre());
        return foundRole.orElseGet(() -> {
            roleRepository.saveAndFlush(role);
            return roleRepository.findByNombre(role.getNombre()).orElse(null);
        });
    }
    @Transactional
    public Usuario getOrSaveUser(Usuario user) {
        Optional<Usuario> foundUser = usuarioRepository.findByCorreo(user.getCorreo());
        return foundUser.orElseGet(() -> {
            Role userRole = user.getRole();
            if (userRole != null) {
                if (userRole.getId_role() == null) {
                    userRole = getOrSaveRole(userRole);
                }
            }
            user.setRole(userRole);
            return usuarioRepository.saveAndFlush(user);
        });
    }
    @Transactional
    public void saveUserRoles(Long userId, Long roleId) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario != null) {
            Role newRole = roleRepository.findById(roleId).orElse(null);
            if (newRole != null) {
                usuario.setRole(newRole);
                usuarioRepository.save(usuario);
            }
        }
    }

    @Transactional
    public void createTipoPago(String nombre) {
        Optional<Tipo_Pago> foundTipoPago = tipoPagoRepository.findByNombre(nombre);
        if (foundTipoPago.isEmpty()) {
            Tipo_Pago tipoPago = new Tipo_Pago();
            tipoPago.setNombre(nombre);
            tipoPagoRepository.saveAndFlush(tipoPago);
        }
    }

    @Transactional
    public void createTipoHabitacion(String nombrehabitacion){
        Optional<Tipo_habitacion> foundTipoHab = tipoHabitacionRepository.findByNombrehabitacion(nombrehabitacion);
        if (foundTipoHab.isEmpty()){
            Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
            tipoHabitacion.setNombrehabitacion(nombrehabitacion);
            tipoHabitacionRepository.saveAndFlush(tipoHabitacion);
        }
    }

    @Transactional
    public void createCategoria(String nombrecategoria){
        Optional<Categoria> foundCategoria = categoriaRepository.findByNombrecategoria(nombrecategoria);
        if (foundCategoria.isEmpty()){
            Categoria categoria = new Categoria();
            categoria.setNombrecategoria(nombrecategoria);
            categoriaRepository.saveAndFlush(categoria);
        }
    }

}
