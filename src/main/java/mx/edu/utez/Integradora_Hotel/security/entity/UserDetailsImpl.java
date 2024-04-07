package mx.edu.utez.Integradora_Hotel.security.entity;

import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private String correo;
    private String contrasena;
    private boolean blocked;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String correo, String contrasena, boolean blocked, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.blocked = blocked;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(usuario.getRole().getNombre()));
        return new UserDetailsImpl(
                usuario.getCorreo(), usuario.getContrasena(), usuario.getStatus(), usuario.getStatus(), authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
