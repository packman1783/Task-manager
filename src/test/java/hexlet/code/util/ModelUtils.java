package hexlet.code.util;

import hexlet.code.mapper.UserMapper;
import hexlet.code.model.User;

import net.datafaker.Faker;

import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.instancio.Instancio;

import lombok.Getter;

@Getter
@Component
public class ModelUtils {

    @Autowired
    private Faker faker;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    private User user;

    public ModelUtils generateData() {
        createUser();

        return this;
    }

    private void createUser() {
        var encodedPassword = passwordEncoder.encode(faker.internet().password(3, 20));
        user = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .supply(Select.field(User::getFirstName), () -> faker.name().firstName())
                .supply(Select.field(User::getLastName), () -> faker.name().lastName())
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .supply(Select.field(User::getEncryptedPassword), () -> encodedPassword)
                .ignore(Select.field(User::getCreatedAt))
                .ignore(Select.field(User::getUpdatedAt))
                .create();
    }
}
