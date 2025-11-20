package tech.buildrun.agregadorinvestimentos.Repository;

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository --> interface do Spring Data JPA com os metodos prontos
import org.springframework.stereotype.Repository;
import tech.buildrun.agregadorinvestimentos.Entity.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>{
}

//extends JpaRepository<User, UUID> --> diz que vai controlar objetos User, cuja chave primaria é do tipo UUID
