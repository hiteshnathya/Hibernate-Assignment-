package org.example.dao;
import com.claims.entity.Policy;
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
public class PolicyDAOTest {

    @Autowired
    private PolicyDAO policyDAO;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void setup() {
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void testSavePolicy() {
        Policy policy = new Policy();
        policy.setPolicyNumber("POL123");
        policy.setPolicyHolderName("John Doe");
        policy.setPolicyEffectiveDate(new Date());
        policy.setPolicyExpirationDate(new Date());
        policy.setPremiumAmount(100.0);

        policyDAO.savePolicy(policy);

        assertNotNull(policy.getId());
    }

    @Test
    public void testGetPolicyById() {
        Policy policy = policyDAO.getPolicyById(1L);

        assertNotNull(policy);
        assertEquals("POL123", policy.getPolicyNumber());
    }

    @Test
    public void testGetAllPolicies() {
        List<Policy> policies = policyDAO.getAllPolicies();

        assertNotNull(policies);
        assertEquals(1, policies.size());
    }

    @Test
    public void testDeletePolicy() {
        Policy policy = policyDAO.getPolicyById(1L);

        policyDAO.deletePolicy(policy.getId());

        Policy deletedPolicy = policyDAO.getPolicyById(1L);

        assertNull(deletedPolicy);
    }
}