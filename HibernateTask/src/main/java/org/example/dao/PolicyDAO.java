package com.claims.dao;

import com.claims.entity.Policy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PolicyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void savePolicy(Policy policy) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(policy);
    }

    public Policy getPolicyById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Policy.class, id);
    }

    public List<Policy> getAllPolicies() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Policy", Policy.class).getResultList();
    }

    public void deletePolicy(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Policy policy = session.get(Policy.class, id);
        session.delete(policy);
    }
}