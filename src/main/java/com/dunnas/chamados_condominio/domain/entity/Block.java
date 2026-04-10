package com.dunnas.chamados_condominio.domain.entity;

public class Block {
    private Long id;
    private int qtdFloors;
    private int nUnitsPerFloor;
    private String identity;

    public Block(int qtdFloors, int nUnitsPerFloor, String identity) {
        this.qtdFloors = qtdFloors;
        this.nUnitsPerFloor = nUnitsPerFloor;
        this.identity = identity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtdFloors() {
        return qtdFloors;
    }

    public void setQtdFloors(int qtdFloors) {
        this.qtdFloors = qtdFloors;
    }

    public int getnUnitsPerFloor() {
        return nUnitsPerFloor;
    }

    public void setnUnitsPerFloor(int nUnitsPerFloor) {
        this.nUnitsPerFloor = nUnitsPerFloor;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
