package com.hadev.blog.user;


import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestPropertySource("classpath:application.yml")
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);



    @Autowired
    private UserRepository userRepository;

    private UserEntity initUser;


    @BeforeAll
    void setUp(){
//        MockitoAnnotations.openMocks(this);
//        userService = new UserService(userRepository);
        // given
        initUser = UserEntity.builder()
                .userId("USER_01")
                .userEmail("hello@naver.com")
                .userPassword("12345")
                .address("용인시 수지구 정든로 16")
                .age(28)
                .name("윤태")
                .registDate(new Timestamp(System.currentTimeMillis()))
                .modifyDate(new Timestamp(System.currentTimeMillis()))
                .build();
        logger.info("Init User Infomation !!");
        logger.info(initUser.toString());
    }

    @Test
    @Order(0)
    @DisplayName("단일 유저 생성 테스트")
    @Rollback(false)
    void TestA_userCreate(){
        // when
        UserEntity ent = userRepository.save(initUser);

        // then
        assertEquals(ent.toString(), initUser.toString());
    }

    @Test
    @Order(1)
    @DisplayName("단일 유저 생성 후 조회 테스트")
    @Rollback(false)
    void TestB_userSelect(){
        // given
        userRepository.save(initUser);

        // when
        UserEntity ent = userRepository.findById(initUser.getUserId()).orElseGet(UserEntity::new);

        // then
        assertEquals(ent.toString(), initUser.toString());
    }

    @Test
    @Order(2)
    @DisplayName("단일 유저 생성 후 수정 테스트")
    @Rollback(false)
    void TestC_userUpdate(){
        // given
        userRepository.save(initUser);

        // when
        UserEntity ent = userRepository.findById(initUser.getUserId()).orElseGet(UserEntity::new);
        ent.setUserEmail("qkrdbsxo1@naver.com");
        UserEntity ent2 = userRepository.findById(initUser.getUserId()).orElseGet(UserEntity::new);

        // then
        assertEquals(ent.toString(), ent2.toString());
    }

    @Test
    @Order(3)
    @DisplayName("단일 유저 생성 후 삭제 테스트")
    @Rollback(false)
    void TestD_userDelete(){
        // given
        userRepository.save(initUser);

        // when
        userRepository.delete(initUser);
        UserEntity ent = userRepository.findByUserId(initUser.getUserId()).orElseGet(UserEntity::new);
        UserEntity nullObject = new UserEntity();

        // given
        assertEquals(nullObject.toString(), ent.toString());

    }
}
