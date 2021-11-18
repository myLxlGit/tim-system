package com.gre.lxl.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;


/**
 * @author lxl
 * @date 2021/02/24 10:46
 */
@Configuration
public class JpaConfig {

    /**
     * 让Spring管理JPAQueryFactory
     *
     * @param entityManager entityManager
     * @return JPAQueryFactory
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
