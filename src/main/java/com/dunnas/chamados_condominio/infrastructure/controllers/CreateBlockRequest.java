package com.dunnas.chamados_condominio.infrastructure.controllers;

public record CreateBlockRequest(int qtdFloors, int nUnitsPerFloor, String identity) {

}
