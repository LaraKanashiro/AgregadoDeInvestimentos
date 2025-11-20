package tech.buildrun.agregadorinvestimentos.Service;

import org.springframework.stereotype.Service;
import tech.buildrun.agregadorinvestimentos.Controller.CreateUserDto;
import tech.buildrun.agregadorinvestimentos.Controller.UpdateUserDto;
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


    public void UpdateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }

            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);

        }

    }


    public void deleteUserById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }
}
