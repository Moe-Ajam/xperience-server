package com.moecodes.xperienceserver.bootstrap;

import com.moecodes.xperienceserver.domain.Task;
import com.moecodes.xperienceserver.domain.Player;
import com.moecodes.xperienceserver.repositories.TaskRepository;
import com.moecodes.xperienceserver.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final PlayerRepository playerRepository;

    public BootstrapData(TaskRepository taskRepository, PlayerRepository playerRepository) {
        this.taskRepository = taskRepository;
        this.playerRepository = playerRepository;
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

        Player moeSaved = playerRepository.save(moe);
        laundry.setUser(moeSaved);
        dishes.setUser(moeSaved);

        Task laundrySaved = taskRepository.save(laundry);
        Task dishesSaved = taskRepository.save(dishes);

        moeSaved.getTasks().add(laundrySaved);
        moeSaved.getTasks().add(dishesSaved);

        System.out.println("In Bootstrap");
        System.out.println("User count: " + playerRepository.count());
        System.out.println("Task count: " + taskRepository.count());
    }
}
