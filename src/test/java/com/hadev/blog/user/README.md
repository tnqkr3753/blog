## Mockito

### Mockito verify
> [[JUnit & Mockito] Verify Method Calls](https://velog.io/@dnjscksdn98/JUnit-Mockito-Verify-Method-Calls)

해당 테스트 안에서 특정 메소드를 호출했는지 검증할 때 사용   
```java
- Mockito.verify(mock).method(param)  # Mock 객체의 특정 메소드 호출 검증
- Mockito.verify(mock, times(wantedNumberOfInvocations)).method(param) # Mock 객체의 메소드가 정해진 횟수만큼 호출됬는지 검증
- Mockito.verify(mock, atLeast(minNumberOfInvocations)).method(param) # Mock 객체의 메소드가 최소 정해진 횟수만큼 호출됬는지 검증
- Mockito.verify(mock, atLeastOnce()).method(param) # Mock 객체의 메소드가 최소 한번 호출됬는지 검증
- Mockito.verify(mock, atMost(maxNumberOfInvocations)).method(param) # Mock 객체의 메소드가 정해진 횟수보다 적게 호출됬는지 검증
- Mockito.verify(mock, never()).method(param) # Mock 객체의 메소드가 호출안됨을 검증
```

필요에 따라 가끔 검증이 필요한 메소드에 전달값까지 확인해야 할 때 ArgumentCaptor 클래스 사용
```
- Mockito.verify(mock).add(captor.capture())
- assertEquels("~~", captor.getValue())
```

## DataJpaTest Annotation
@DataJpaTest 어노테이션은 JPA 관련 테스트 설정만 로드
DataSource의 설정이 정상인지와 JPA를 사용하여 데이터를 CRUD하는 등을 테스트 할 수 있음
포함 어노테이션
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@BootstrapWith(DataJpaTestContextBootstrapper.class)
@ExtendWith(SpringExtension.class)
@OverrideAutoConfiguration(enabled = false)
@TypeExcludeFilters(DataJpaTypeExcludeFilter.class)
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@ImportAutoConfiguration
```