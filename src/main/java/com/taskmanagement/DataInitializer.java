package com.taskmanagement;

import com.taskmanagement.model.Project;
import com.taskmanagement.model.Task;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.ProjectRepository;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create a user
        User user = new User("user", passwordEncoder.encode("password"), "user@example.com", "ROLE_USER");
        userRepository.save(user);

        // Create projects
        Project project1 = new Project("Website Redesign", "Complete redesign of the company website.");
        projectRepository.save(project1);

        Project project2 = new Project("Mobile App Development", "Develop a new mobile app for iOS and Android.");
        projectRepository.save(project2);

        // Create tasks for Project 1
        taskRepository.save(new Task("Design Mockups", "Create new mockups for all website pages.", "In Progress", LocalDate.now().plusDays(10), project1, user));
        taskRepository.save(new Task("Develop Homepage", "Implement the new homepage design.", "To Do", LocalDate.now().plusDays(20), project1, user));

        // Create tasks for Project 2
        taskRepository.save(new Task("Plan Features", "Define the core features for the mobile app.", "Done", LocalDate.now().minusDays(5), project2, user));
        taskRepository.save(new Task("Setup Development Environment", "Configure the build tools and CI/CD pipeline.", "In Progress", LocalDate.now().plusDays(5), project2, null));
    }
}
