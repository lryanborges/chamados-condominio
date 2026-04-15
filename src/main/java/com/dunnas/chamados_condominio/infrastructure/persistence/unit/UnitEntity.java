package com.dunnas.chamados_condominio.infrastructure.persistence.unit;

import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "unit")
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long blockId;
    private int floor;
    private String identifier;
    @ManyToMany
    @JoinTable(
            name = "user_unit",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;

    public UnitEntity() {}

    public UnitEntity(Long blockId, int floor, String identifier) {
        this.blockId = blockId;
        this.floor = floor;
        this.identifier = identifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
