package mx.edu.utez.Integradora_Hotel.service.usuario;

import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.model.role.RoleRepository;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import mx.edu.utez.Integradora_Hotel.model.usuario.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(usuarioRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> register(Usuario usuario) {
        System.out.println(usuario.toString());
        Optional<Usuario> foundUsuario = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (foundUsuario.isPresent())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,
                    true, "EmailAlreadyExist"),
                    HttpStatus.BAD_REQUEST);

        usuario.setStatus(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setContrasena(encoder.encode(usuario.getContrasena()));
        usuario = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<>(new ApiResponse(
                usuario,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
//sdd
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        Optional<Usuario> findById = usuarioRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"),
                    HttpStatus.BAD_REQUEST);
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Usuario eliminado"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        Optional<Usuario> findById = usuarioRepository.findById(id);
        if (findById.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "No se encontró el Id"),
                    HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(usuarioRepository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})

    public ResponseEntity<ApiResponse> update(Long id, Usuario updateUsuario){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(updateUsuario.getNombre());
            usuario.setApellidoM(updateUsuario.getApellidoM());
            usuario.setApellidoM(updateUsuario.getApellidoP());
            usuario.setCorreo(updateUsuario.getCorreo());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "Usuario actualizado"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "No se encontró el Id proporcionado"), HttpStatus.NOT_FOUND);
        }
    }

}
