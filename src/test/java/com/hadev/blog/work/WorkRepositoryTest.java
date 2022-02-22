package com.hadev.blog.work;


import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import com.hadev.blog.work.entity.WorkEntity;
import com.hadev.blog.work.repository.WorkRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.util.List;

@DataJpaTest
@TestPropertySource("classpath:application.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(WorkRepositoryTest.class);

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity initUser;

    private WorkEntity initWork;

    @BeforeAll
    @Rollback(value = false)
    void setUp(){
        // Create User
        initUser = UserEntity.builder()
                .userId("USER_01")
                .userEmail("hello@naver.com")
                .userPassword("12345")
                .address("용인시 수지구 정든로 16")
                .age(28)
                .name("윤태")
                .build();
        userRepository.save(initUser);
        // Create Work
        initWork = WorkEntity.builder()
                .workName("JPA Study")
                .workEndDate(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .workStartDate(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
                .workStatus("Doing")
                .workType("Study")
                .user(initUser)
                .build();
    }

    @Test
    @Order(0)
    @DisplayName("작업 생성")
    @Rollback(value = false)
    void TestA_createWork(){
        WorkEntity ent = workRepository.save(initWork);
        logger.info(ent.toString());
        Assertions.assertEquals(initWork.getWorkName(), ent.getWorkName());
    }

    @Test
    @Order(1)
    @DisplayName("작업 전체 조회")
    void TestB_selectWork(){
        List<WorkEntity> workEntities = workRepository.findAll();
        logger.info(workEntities.toString());
        Assertions.assertEquals(1, workEntities.get(0).getWorkId());
    }

    @Test
    @Order(2)
    @DisplayName("유저 정보로 작업 조회")
    void TestC_selectWorkByUser(){
        List<WorkEntity> workEntities = workRepository.findAllByUser_UserId(initUser.getUserId());
        logger.info(workEntities.toString());
        Assertions.assertEquals(1, workEntities.get(0).getWorkId());
    }

    @Test
    @Order(3)
    @DisplayName("유저 조회 후 그 안의 작업과 유저정보로 작업조회 비교")
    void TestD_selectUser(){
        // given

        // when
        UserEntity userEntity = userRepository.findByUserId(initUser.getUserId()).orElseGet(UserEntity::new);
        List<WorkEntity> workEntities = workRepository.findAllByUser_UserId(initUser.getUserId());

        // then
        Assertions.assertEquals(userEntity.getWorks(), workEntities);
        // 여기서의 결론, workRepo의 영속성 객체와 userRepo의 user영속성객체 안의 work객체 다름
    }

}
