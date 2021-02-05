package ru.milov.transactions.service.entity;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    @Transient
    @ManyToMany(mappedBy = "role_id")
    private Set<User> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}