package br.com.project.SB.NameProject.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Boolean active;

    public User(String username, String password, UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    public void delete(){
        this.active = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_INTERN"));
        else if (this.role == UserRole.USER) return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_INTERN"));
        else return List.of(new SimpleGrantedAuthority("ROLE_INTERN"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
