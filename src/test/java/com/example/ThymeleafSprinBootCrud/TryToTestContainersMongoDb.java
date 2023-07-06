package com.example.ThymeleafSprinBootCrud;

import com.example.ThymeleafSprinBootCrud.entity.User;
import com.example.ThymeleafSprinBootCrud.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class TryToTestContainersMongoDb {

    private final User user1 = new User("ivan", 21, "1238125673");
    private final User user2 = new User("wouaeh", 43, "123554645");
    private final User user3 = new User("waoueh", 56, "12334534");
    private final User user4 = new User("grecj", 23, "123123543");

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    @Autowired
    public UserRepository userRepository;


    @Autowired
    ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.save(user4);
    }


    @AfterEach
    void cleanUp() {
        this.userRepository.deleteAll();
    }

    @Test
    void when_GetUser_Expect_ListSizeEqual_4() {
        List<User> userList = userRepository.findAll();
        assertEquals(4, userList.size());
    }

    @Test
    void when_SaveUser_Expect_ListSizeEqual_4() {
        User user = new User("name", 15, "423372846372");
        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        assertEquals(5, userList.size());
    }

    @Test
    void when_DeleteUser_Expect_ListSizeEqual_3() {
        userRepository.delete(user1);
        List<User> userList = userRepository.findAll();
        assertEquals(3, userList.size());
    }

    @Test
    void when_GetUserById_Expect_FindOneUser() {
        Optional<User> any = userRepository.findAll().stream().findAny();
        assertNotNull(any.get());
    }
}
