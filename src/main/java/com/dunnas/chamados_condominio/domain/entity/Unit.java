package com.dunnas.chamados_condominio.domain.entity;

import java.util.List;

public class Unit {
    private Long id;
    private Long blockId;
    private int floor;
    String identifier;
    private List<User> users;

    public Unit(Long blockId, int floor, String identifier) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
