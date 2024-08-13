package com.claims.dao;

import com.claims.entity.Claim;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClaimDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveClaim(Claim claim) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(claim);
    }

    public Claim getClaimById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Claim.class, id);
    }

    public List<Claim> getAllClaims() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Claim", Claim.class).getResultList();
    }

    public void deleteClaim(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Claim claim = session.get(Claim.class, id);
        session.delete(claim);
    }
}