package tech.buildrun.agregadorinvestimentos.Service;

import org.springframework.stereotype.Service;
import tech.buildrun.agregadorinvestimentos.Controller.CreateUserDto;
import tech.buildrun.agregadorinvestimentos.Entity.User;
import tech.buildrun.agregadorinvestimentos.Repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    // injetando dependencia
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserDto createUserDto) {

        // DTO --> ENTITY
       var entity =  new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(), Instant.now(),
                null);

                userRepository.save(entity);
    }
}
