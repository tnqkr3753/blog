package com.hadev.blog.work.repository;


//static으로 선언된 workEntity(QWorkEntity) 사용
import static com.hadev.blog.work.entity.QWorkEntity.workEntity;
import com.hadev.blog.work.entity.WorkEntity;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class WorkCustomRepositoryImpl implements WorkCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public WorkCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }
    
    @Override
    public List<WorkEntity> findAllByPeriod() {
        return jpaQueryFactory.selectFrom(workEntity)
                .where(workEntity.workStartDate.before(LocalDateTime.now()))
                .where(workEntity.workEndDate.after(LocalDateTime.now()))
                .fetch();
    }

    @Override
    public List<WorkEntity> findAllByUserId(String userId) {
        return jpaQueryFactory.selectFrom(workEntity)
                .where(workEntity.user.userId.eq(userId))
                .fetch();
    }
}
