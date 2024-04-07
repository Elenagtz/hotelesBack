package mx.edu.utez.Integradora_Hotel.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.controller.paquete.PaqueteDto;
import mx.edu.utez.Integradora_Hotel.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = {"*"})
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return usuarioService.getAll();
    }

    @PostMapping("/registro/")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody UsuarioDto usuarioDto){
        return usuarioService.register(usuarioDto.toEntity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        return usuarioService.delete(id);
    }
//dsd

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto) {
        ResponseEntity<ApiResponse> updateResponse = usuarioService.update(id, usuarioDto.toEntity());
        return updateResponse;
    }
}
