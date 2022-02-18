package com.hadev.blog.user;


import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DataJpaTest
@TestPropertySource("classpath:application.yml")
public class UserCRUDTest {
    @Mock
    private UserService userService;

    private UserEntity initUser;

    @BeforeEach
    void setUp(){
        initUser = UserEntity.builder()
                .userId("USER_01")
                .userPassword("12345")
                .address("용인시 수지구 정든로 16")
                .age(28)
                .name("윤태")
                .registDate(new Timestamp(System.currentTimeMillis()))
                .modifyDate(new Timestamp(System.currentTimeMillis()))
                .build();
        userService.save(initUser);
    }

    @Test
    @DisplayName("유저 조회가 된다.")
    void saveUserTest(){
        UserEntity ent = userService.findById(initUser.getUserId());
        assertThat(ent, is(equalTo(initUser)));
    }

}
