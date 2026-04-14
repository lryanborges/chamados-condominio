package com.dunnas.chamados_condominio.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.units WHERE u.email = :email")
    UserEntity findByEmail(@Param("email") String email);
}
