package tech.buildrun.agregadorinvestimentos.Service;

import org.springframework.stereotype.Service;
import tech.buildrun.agregadorinvestimentos.Controller.CreateUserDto;
import tech.buildrun.agregadorinvestimentos.Entity.User;
import tech.buildrun.agregadorinvestimentos.Repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    // injetando dependencia
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        // DTO --> ENTITY
       var entity =  new User(

                null, // --> UUID.randomUUID()
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
               null);

          var userSaved = userRepository.save(entity);

          // dessa forma ele vai retonar somente o identicador
          return userSaved.getId();
    }

    public Optional <User> getUserById(String userId) {

       return userRepository.findById(UUID.fromString(userId));
    }

    // trazer todos os ususrios do banco de dados para dentro dessa lista
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
