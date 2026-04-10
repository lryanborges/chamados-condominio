package com.dunnas.chamados_condominio.infrastructure.persistence;

import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
