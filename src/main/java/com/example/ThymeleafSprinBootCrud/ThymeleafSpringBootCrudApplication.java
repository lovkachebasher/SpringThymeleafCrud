package com.example.ThymeleafSprinBootCrud;

import com.example.ThymeleafSprinBootCrud.entity.User;
import com.example.ThymeleafSprinBootCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafSpringBootCrudApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public ThymeleafSpringBootCrudApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafSpringBootCrudApplication.class, args);
    }

    //TODO ci/cd должно срабатывать когда пушишь в мастер --> для начала этого будет достаточно,
    // позже сделать
    // --дев стенд-- и --тест стенд--
    // переименовать ветку мастер в dev
    //Check

    @Override
    public void run(String... args) {
        userRepository.deleteAll();

        User user1 = new User("ivan", 21, "1238125673");
        User user2 = new User("wouaeh", 43, "123554645");
        User user3 = new User("waoueh", 56, "12334534");
        User user4 = new User("grecj", 23, "123123543");
        User user5 = new User("roman", 65, "10238190238");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        System.out.println("--------------------------------");
        userRepository.findAll().forEach(System.out::println);
    }
}
