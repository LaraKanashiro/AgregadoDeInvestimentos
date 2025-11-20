package tech.buildrun.agregadorinvestimentos.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.agregadorinvestimentos.Entity.User;
import tech.buildrun.agregadorinvestimentos.Repository.UserRepository;
import tech.buildrun.agregadorinvestimentos.Service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    // injetando dependencia
    private UserRepository userRepository;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody CreateUserDto createUserDto) {
       var userId =  userService.createUser(createUserDto);

       //localizaçao do projeto v1/users
        return ResponseEntity.created(URI.create("v1/users" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById( @PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    // listagem dos ususarios (users)
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
       var users = userService.listUsers();
       return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
       userService.UpdateUserById(userId, updateUserDto);
       return ResponseEntity.ok().build();
    }



    // deletando User pelo Id
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
      userService.deleteUserById(userId);
      return ResponseEntity.noContent().build();
    }

}
