package com.dunnas.chamados_condominio.infrastructure.controllers.api.block;

public record CreateBlockRequest(int qtdFloors, int nUnitsPerFloor, String identity) {

}
