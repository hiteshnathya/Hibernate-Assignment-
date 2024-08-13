package org.example.dao;

import com.claims.entity.Claim;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ClaimDAOTest {

    @Autowired
    private ClaimDAO claimDAO;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void setup() {
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void testSaveClaim() {
        Claim claim = new Claim();
        claim.setClaimDate(new Date());
        claim.setClaimType("Accident");
        claim.setClaimStatus("Open");
        claim.setDescription("Car accident claim");

        claimDAO.saveClaim(claim);

        assertNotNull(claim.getId());
    }

    @Test
    public void testGetClaimById() {
        Claim claim = claimDAO.getClaimById(1L);

        assertNotNull(claim);
        assertEquals("Accident", claim.getClaimType());
    }

    @Test
    public void testGetAllClaims() {
        List<Claim> claims = claimDAO.getAllClaims();

        assertNotNull(claims);
        assertEquals(1, claims.size());
    }

    @Test
    public void testDeleteClaim() {
        Claim claim = claimDAO.getClaimById(1L);

        claimDAO.deleteClaim(claim.getId());

        Claim deletedClaim = claimDAO.getClaimById(1L);

        assertNull(deletedClaim);
    }
}