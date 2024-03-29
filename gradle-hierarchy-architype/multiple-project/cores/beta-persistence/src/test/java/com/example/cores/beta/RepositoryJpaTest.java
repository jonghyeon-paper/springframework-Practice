package com.example.cores.beta;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.beta.jpa.entity.QSample;
import com.example.cores.beta.jpa.entity.Sample;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest(classes = { CoresBetaPersistenceScanner.class })
@SpringBootConfiguration
public class RepositoryJpaTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void doQuerydslTest() {
        System.out.println(entityManager);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        QSample qSample = new QSample("a");
        QueryResults<Sample> queryResult = jpaQueryFactory.selectFrom(qSample)
                .where(qSample.name.in(Arrays.asList("americano", "choco")),
                        qSample.enabled.eq(true))
                .fetchResults();

        queryResult.getResults().forEach(System.out::println);
    }
}
