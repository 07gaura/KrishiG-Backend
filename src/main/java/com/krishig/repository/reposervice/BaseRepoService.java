package com.krishig.repository.reposervice;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepoService<T, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract Class<T> getEntityClass();

    protected Session getSession() { return entityManager.unwrap(Session.class);}

    @Bean
    public EntityManager getEntityManager(){ return entityManager; }

    public <S extends T> S save(S entity) { return getRepository().save(entity);}

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<S>();

        for(S entity : entities) {
            result.add(save(entity));
        }

        return result;
    }

    protected Criteria getCriteria() { return getSession().createCriteria(getEntityClass());}
}
