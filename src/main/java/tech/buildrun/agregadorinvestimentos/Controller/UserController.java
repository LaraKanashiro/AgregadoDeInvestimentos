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


    // @PostMapping --> aqui eu estou criando o usuario
    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody CreateUserDto createUserDto) {
       var userId =  userService.createUser(createUserDto);

       //localizaçao do projeto v1/users
        return ResponseEntity.created(URI.create("v1/users" + userId.toString())).build();
    }

    // @GetMapping -->  aqui eu estou buscando o usuario pelo Id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById( @PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        // se o usuario existir ele retorna 200 + JSON do usuario
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        // se o usuario nao exixtir ele retorna HTTP 404 Not Found
        else {
            return ResponseEntity.notFound().build();
        }

    }

    // @GetMapping --> mais um dele, mas agora é para a  listagem de todos os  ususarios (users)
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
       var users = userService.listUsers(); // userService.listUsers() -->  busca todos os usuarios do banco.
       return ResponseEntity.ok(users); // ResponseEntity.ok(users) --> 200 + JSON da lista.
    }

    // @PutMapping --> atualizar usuario
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
       userService.UpdateUserById(userId, updateUserDto);
       return ResponseEntity.ok().build();
    }



    // @DeleteMapping --> deletando User pelo Id
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
      userService.deleteUserById(userId); // userService.deleteUserById(userId) --> pedido de exclusao ao service.
      return ResponseEntity.noContent().build();
    }

}
