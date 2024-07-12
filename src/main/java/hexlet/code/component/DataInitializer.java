package hexlet.code.component;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.mapper.LabelMapper;
import hexlet.code.model.User;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.CustomUserDetailsService;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.repository.TaskStatusRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    private final CustomUserDetailsService userService;

    private final TaskStatusRepository taskStatusRepository;

    private final TaskStatusMapper taskStatusMapper;

    private final LabelRepository labelRepository;

    private final LabelMapper labelMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addFirstUser();
        addDefaultTaskStatuses();
        addDefaultLabels();
    }


    private void addFirstUser() {
        var email = "hexlet@example.com";
        var password = "qwerty";

        if (userRepository.findByEmail(email).isEmpty()) {
            var userData = new User();
            userData.setEmail(email);
            userData.setEncryptedPassword(password);
            userService.createUser(userData);
        }
    }

    private void addDefaultTaskStatuses() {
        var defaultStatuses = Map.of(
                "draft", "Draft",
                "to_review", "To review",
                "to_be_fixed", "To be fixed",
                "to_publish", "To publish",
                "published", "Published"
        );
        for (var entry : defaultStatuses.entrySet()) {
            if (taskStatusRepository.findBySlug(entry.getKey()).isEmpty()) {
                var taskStatusCreateDTO = new TaskStatusCreateDTO();
                taskStatusCreateDTO.setSlug(entry.getKey());
                taskStatusCreateDTO.setName(entry.getValue());
                var taskStatus = taskStatusMapper.map(taskStatusCreateDTO);
                taskStatusRepository.save(taskStatus);
            }
        }
    }

    private void addDefaultLabels() {
        var defaultLabels = List.of(
                "feature",
                "bug"
        );

        for (var labelName : defaultLabels) {
            if (labelRepository.findByName(labelName).isEmpty()) {
                var labelCreateDTO = new LabelCreateDTO();
                labelCreateDTO.setName(labelName);
                var label = labelMapper.map(labelCreateDTO);
                labelRepository.save(label);
            }
        }
    }
}
