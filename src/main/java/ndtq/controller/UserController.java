package ndtq.controller;

import ndtq.model.Role;
import ndtq.model.Songs;
import ndtq.model.Tags;
import ndtq.model.Users;
import ndtq.service.users.IUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        String name = user.getUsername();
        Optional<Users> users = userService.findUserByUsername(name);
        if (users.isPresent()) {
            if (Objects.equals(user.getPassword(), users.get().getPassword())) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong Password", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Username is not Present", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<Iterable<Users>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        if (userService.checkUsername(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setAvatar("https://iupac.org/wp-content/uploads/2018/05/default-avatar.png");
        user.setRole(new Role(1L,"USER"));
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users users) {
        Optional<Users> user = userService.findById(id);
        if (user.isPresent()) {
            // set lại avt cũ khi k up ảnh
            if (users.getAvatar() == null){
                users.setAvatar(user.get().getAvatar());
            }
            // lấy lại mật khẩu của user cũ gán cho user mới
            users.setPassword(user.get().getPassword());
            users.setUsername(user.get().getUsername());
            // gán id cho user mới
            users.setId(id);
            // lấy lại role của user cũ gán cho user mới
            users.setRole(user.get().getRole());
            // save user mơí
            userService.save(users);
            return new ResponseEntity<>(userService.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/changePass/{id}")
    public ResponseEntity<Users> updatePass(@PathVariable Long id,
                                            @RequestParam String password) {
        Optional<Users> usersOptional = userService.findById(id);
        if (usersOptional.isPresent()) {
            userService.updatePass(id, password);
            Users user = userService.findById(id).get();
            user.setPassword(password);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @GetMapping("/usernames")
    public ResponseEntity<List<String>> getAllUsername() {
        return new ResponseEntity<>(userService.findAllUsername(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Users>> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/countByUser/{id}")
    public ResponseEntity<List<Integer>> countByUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.countByUser(id), HttpStatus.OK);
    }
}
