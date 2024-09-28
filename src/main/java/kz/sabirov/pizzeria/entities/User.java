package kz.sabirov.pizzeria.entities;

import jakarta.persistence.*;
import kz.sabirov.pizzeria.security.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private double balance;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;
    private String token;
    @OneToOne(fetch = FetchType.EAGER)
    private GeoLocation geoLocations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }
    public void setDefaultPermission(){
        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(new Permission(2L, "ROLE_USER"));
        this.permissions = permissionList;
    }
    public void setBanned(){
        this.permissions.add(new Permission(100L, "ROLE_BANNED"));
    }
    public void setUnbanned(){
        this.permissions.remove(new Permission(100L, "ROLE_BANNED"));
    }



    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
