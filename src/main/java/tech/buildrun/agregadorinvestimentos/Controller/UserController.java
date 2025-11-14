package tech.buildrun.agregadorinvestimentos.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.agregadorinvestimentos.Entity.User;
import tech.buildrun.agregadorinvestimentos.Repository.UserRepository;
import tech.buildrun.agregadorinvestimentos.Service.UserService;

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
        userService.createUser(createUserDto);
        return null;
    }

    @GetMapping
    public ResponseEntity<User> getUserById( @PathVariable("userId") String userId) {
        return null;
    }

}
