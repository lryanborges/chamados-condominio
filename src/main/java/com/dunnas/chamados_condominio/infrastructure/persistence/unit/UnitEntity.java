package com.dunnas.chamados_condominio.infrastructure.persistence.unit;

import com.dunnas.chamados_condominio.domain.entity.Block;
import com.dunnas.chamados_condominio.infrastructure.persistence.block.BlockEntity;
import com.dunnas.chamados_condominio.infrastructure.persistence.user.UserEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unit")
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id")
    private BlockEntity block;
    private int floor;
    private String identifier;
    @ManyToMany(mappedBy = "units")
    private List<UserEntity> users = new ArrayList<>();

    public UnitEntity() {}

    public UnitEntity(BlockEntity block, int floor, String identifier) {
        this.block = block;
        this.floor = floor;
        this.identifier = identifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BlockEntity getBlock() {
        return block;
    }

    public void setBlock(BlockEntity block) {
        this.block = block;
    }
}
