package com.moecodes.xperienceserver.bootstrap;

import com.moecodes.xperienceserver.domain.Task;
import com.moecodes.xperienceserver.domain.Player;
import com.moecodes.xperienceserver.repositories.TaskRepository;
import com.moecodes.xperienceserver.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public BootstrapData(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Player moe = new Player();
        moe.setEmail("mahmoud.othman.ajam@gmail.com");
        moe.setPassword("test");
        moe.setHealth(90);
        moe.setUsername("Blo0dyRed");

        Task laundry = new Task();
        laundry.setTitle("Do the laundry");
        laundry.setDescription("Finish the laundry  today!");

        Task dishes = new Task();
        dishes.setTitle("Do the dishes");
        dishes.setDescription("Finish the dishes  today!");

        Player moeSaved = userRepository.save(moe);
        laundry.setUser(moeSaved);
        dishes.setUser(moeSaved);

        Task laundrySaved = taskRepository.save(laundry);
        Task dishesSaved = taskRepository.save(dishes);

        moeSaved.getTasks().add(laundrySaved);
        moeSaved.getTasks().add(dishesSaved);

        System.out.println("In Bootstrap");
        System.out.println("User count: " + userRepository.count());
        System.out.println("Task count: " + taskRepository.count());
    }
}
