package com.dunnas.chamados_condominio.infrastructure.persistence;

import com.dunnas.chamados_condominio.domain.entity.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@SQLRestriction("deleted_at IS NULL")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String scope;
    @ManyToMany
    @JoinTable(
            name = "user_unit",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    private List<UnitEntity> units = new ArrayList<>();
    private LocalDateTime deletedAt;

    public UserEntity() {}

    public UserEntity(String name, String email, String password, Role role, String scope) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.scope = scope;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<UnitEntity> getUnits() {
        return units;
    }

    public void setUnitIds(List<UnitEntity> units) {
        this.units = units;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
