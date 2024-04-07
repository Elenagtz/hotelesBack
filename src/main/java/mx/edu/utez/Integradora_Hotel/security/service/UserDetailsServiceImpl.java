package mx.edu.utez.Integradora_Hotel.security.service;

import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import mx.edu.utez.Integradora_Hotel.security.entity.UserDetailsImpl;
import mx.edu.utez.Integradora_Hotel.service.usuario.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService service;

    public UserDetailsServiceImpl(UsuarioService service) {
        this.service = service;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> foundUser = service.findByEmail(username);
        System.out.println(foundUser.toString());
        if (foundUser.isPresent())
            return UserDetailsImpl.build(foundUser.get());
        throw new UsernameNotFoundException("UserNotFound");
    }
}
