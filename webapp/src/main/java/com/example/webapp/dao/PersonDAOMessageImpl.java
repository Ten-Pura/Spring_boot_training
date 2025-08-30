package com.example.webapp.dao;

import com.example.webapp.entity.Message;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOMessageImpl implements PersonDAO<Message> {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDAOMessageImpl() {
        super();
    }

    @Override
    public List<Message> getAll() {
        List<Message> list = null;
        CriteriaBuilder bulder =
            entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query =
            bulder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query
            .select(root)
            .orderBy(bulder.asc(root.get("datetime")));
        list = (List<Message>) entityManager
            .createQuery(query)
            .getResultList();
        return list;
    }

    @Override
    public Message findById(long id) {
        return (Message) entityManager.createQuery("from Message where id ="
         + id).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> findByName(String name) {
        return (List<Message>) entityManager.createQuery("from Message where name ='" 
        + name + "'" ).getResultList();
    }

    @Override
    public List<Message> find(String fstr) {
        List<Message> list = null;
        CriteriaBuilder builder =
            entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> query =
            builder.createQuery(Message.class);
        Root<Message> root = query.from(Message.class);
        query.select(root)
            .where(builder.like(root.get("content"), "%" + fstr + "%"));
        list = (List<Message>) entityManager
            .createQuery(query)
            .getResultList();
        return list;
    }

    //使わない
    @Override
    public List<Message> getPase(int pase, int limit) {
        return null;
    }

}
