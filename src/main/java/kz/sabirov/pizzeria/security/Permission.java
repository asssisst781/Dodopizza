package kz.sabirov.pizzeria.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="permissions")
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Permission)) return false;
        Permission o = (Permission) obj;
        return this.role.equals(o.getAuthority());

    }
}