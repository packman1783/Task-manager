package hexlet.code.util;

import hexlet.code.exception.ResourceNotFoundException;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.exception.ResourceForbiddenException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        var email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public void verifyCurrentUser(long id) {
        var currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new ResourceForbiddenException("User not authenticated");
        }

        if (!Objects.equals(currentUser.getId(), id)) {
            throw new ResourceForbiddenException("Access to a resource requested by a client has been forbidden");
        }
    }
}
