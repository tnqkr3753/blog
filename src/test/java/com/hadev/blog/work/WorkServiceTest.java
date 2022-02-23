package com.hadev.blog.work;

import com.hadev.blog.user.entity.UserEntity;
import com.hadev.blog.user.repository.UserRepository;
import com.hadev.blog.user.service.UserService;
import com.hadev.blog.work.entity.WorkEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@TestPropertySource("classpath:application.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WorkServiceTest {

    @Autowired
    private UserRepository userRepository;

    private UserService userService ;



    @BeforeAll
    @Rollback(value = false)
    void setUp(){
        // N+1 확인을 위한 저장
        // given
        userService = new UserService(userRepository);
        for (int cnt = 2; cnt < 22; cnt++) {
            UserEntity user = UserEntity.builder()
                    .userId("USER_"+String.format("%02d",cnt))
                    .userEmail("user"+cnt+"@n.n")
                    .name("user"+cnt)
                    .build();

            user.addWork(WorkEntity.builder()
                    .workName("작업"+cnt)
                    .workType("공부")
                    .workStatus("진행중")
                    .build());
            userService.save(user);
        }
    }

    @Test
    @Order(0)
    @DisplayName("N+1 문제 발생 테스트")
    @Rollback(value = false)
    void TestA_N1Test(){

        // when
        List<String> workNames = userService.findAllWorkName();
        // then
        Assertions.assertEquals(workNames.size(), 20);

        //work table에 select 쿼리가 20개 날라감. 총 21개의 쿼리
    }

    @Test
    @Order(1)
    @DisplayName("N+1 문제 해결 : Join Fetch")
    @Rollback(value = false)
    void TestB_N1SolcutionTest(){

        // when
        List<String> workNames = userService.findAllWorkNameJoinFetch();
        // then
        Assertions.assertEquals(workNames.size(), 20);

        //쿼리 하나에 Inner join 하여 데이터 가져옴.
    }

    @Test
    @Order(2)
    @DisplayName("N+1 문제 해결 : Entity Graph")
    @Rollback(value = false)
    void TestC_N1SolutionTest(){

        // when
        List<String> workNames = userService.findAllWorkNameEntityGraph();

        // then
        Assertions.assertEquals(workNames.size(), 20);

        //쿼리 하나에 Left Outer Join 하여 데이터 가져옴
    }
    
    // 문제점 , 카시디안 곱이 발생하여 Work의 개수만큼 User가 중복발생
    // 1. UserEntity의 works 필드에 List 대신 Set을 사용하여 중복제거
    // 2. Query에 DISTINCT를 사용하여 중복 제거

    // ToMany가 두개 이상일 때 joinfetch를 사용하면 에러
    // JPA에서 Fetch join의 조건 1. ToOne은 몇개든 가능, 2. ToMany는 1개만 가능
    // 해결 : Hibernate의의 deault_batch_fetch_size
    // spring.jpa.properties.hibernate.default_batch_fetch_size=1000

    // 참고 : https://jojoldu.tistory.com/165

}
