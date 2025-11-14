package tech.buildrun.agregadorinvestimentos.Entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp // @CreationTimestamp --> preenche automaticamente com a data/hora em que o registro foi criado
    private Instant creationTimestmp;

    @UpdateTimestamp // @UpdateTimestamp --> atualiza automaticamente com a data/hora da última modificação
    private Instant updateTimestmp;

    public User() {
    }

    public User(UUID id, String username, String email, String password, Instant creationTimestmp, Instant updateTimestmp) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimestmp = creationTimestmp;
        this.updateTimestmp = updateTimestmp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Instant getCreationTimestmp() {
        return creationTimestmp;
    }

    public void setCreationTimestmp(Instant creationTimestmp) {
        this.creationTimestmp = creationTimestmp;
    }

    public Instant getUpdateTimestmp() {
        return updateTimestmp;
    }

    public void setUpdateTimestmp(Instant updateTimestmp) {
        this.updateTimestmp = updateTimestmp;
    }
}
