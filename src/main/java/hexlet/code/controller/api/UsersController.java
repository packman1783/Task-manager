package hexlet.code.controller.api;

import hexlet.code.dto.user.UserCreateDTO;
import hexlet.code.dto.user.UserDTO;
import hexlet.code.dto.user.UserUpdateDTO;
import hexlet.code.service.UsersService;
import hexlet.code.util.UserUtils;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService userService;

    private final UserUtils userUtils;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> index() {
        var users = userService.getAll();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(users.size()))
                .body(users);
    }

    @GetMapping("/{id}")
    public UserDTO show(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody UserCreateDTO data) {
        return userService.create(data);
    }

    @PutMapping("/{id}")
    public UserDTO update(@Valid @RequestBody UserUpdateDTO data, @PathVariable long id) {
        userUtils.verifyCurrentUser(id);
        return userService.update(data, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userUtils.verifyCurrentUser(id);
        userService.delete(id);
    }
}
