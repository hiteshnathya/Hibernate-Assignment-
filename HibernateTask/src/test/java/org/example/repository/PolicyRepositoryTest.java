package org.example.repository;
import com.claims.entity.Policy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PolicyRepositoryTest {

    @Autowired
    private PolicyRepository policyRepository;

    @Before
    public void setup() {
        // Initialize test data
    }

    @Test
    public void testSavePolicy() {
        Policy policy = new Policy();
        policy.setPolicyNumber("POL123");
        policy.setPolicyHolderName("John Doe");
        policy.setPolicyEffectiveDate(new Date());
        policy.setPolicyExpirationDate(new Date());
        policy.setPremiumAmount(100.0);

        policyRepository.savePolicy(policy);

        assertNotNull(policy.getId());
    }

    @Test
    public void testGetPolicyById() {
        Policy policy = policyRepository.getPolicyById(1L);

        assertNotNull(policy);
        assertEquals("POL123", policy.getPolicyNumber());
    }

    @Test
    public void testGetAllPolicies() {
        List<Policy> policies = policyRepository.getAllPolicies();

        assertNotNull(policies);
        assertEquals(1, policies.size());
    }

    @Test
    public void testGetPoliciesByPolicyHolderName() {
        List<Policy> policies = policyRepository.getPoliciesByPolicyHolderName("John Doe");

        assertNotNull(policies);
        assertEquals(1, policies.size());
    }

    @Test
    public void testGetPoliciesByPolicyNumber() {
        Policy policy = policyRepository.getPolicyByPolicyNumber("POL123");

        assertNotNull(policy);
        assertEquals("John Doe", policy.getPolicyHolderName());
    }

    @Test
    public void testDeletePolicy() {
        Policy policy = policyRepository.getPolicyById(1L);

        policyRepository.deletePolicy(policy.getId());

        Policy deletedPolicy = policyRepository.getPolicyById(1L);

        assertNull(deletedPolicy);
    }
}