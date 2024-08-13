package org.example.service;

import com.claims.entity.Policy;
import com.claims.repository.PolicyRepository;
import com.claims.service.PolicyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceTest {

    @InjectMocks
    private PolicyService policyService;

    @Mock
    private PolicyRepository policyRepository;

    @Before
    public void setup() {
        // Initialize test data
    }

    @Test
    public void testCreatePolicy() {
        Policy policy = new Policy();
        policy.setPolicyNumber("POL123");
        policy.setPolicyHolderName("John Doe");
        policy.setPolicyEffectiveDate(new Date());
        policy.setPolicyExpirationDate(new Date());
        policy.setPremiumAmount(100.0);

        when(policyRepository.save(policy)).thenReturn(policy);

        Policy createdPolicy = policyService.createPolicy(policy);

        assertNotNull(createdPolicy);
        assertEquals("POL123", createdPolicy.getPolicyNumber());
    }

    @Test
    public void testGetPolicyById() {
        Policy policy = new Policy();
        policy.setId(1L);
        policy.setPolicyNumber("POL123");
        policy.setPolicyHolderName("John Doe");

        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));

        Policy retrievedPolicy = policyService.getPolicyById(1L);

        assertNotNull(retrievedPolicy);
        assertEquals("POL123", retrievedPolicy.getPolicyNumber());
    }

    @Test
    public void testGetAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        policies.add(new Policy());
        policies.add(new Policy());

        when(policyRepository.findAll()).thenReturn(policies);

        List<Policy> retrievedPolicies = policyService.getAllPolicies();

        assertNotNull(retrievedPolicies);
        assertEquals(2, retrievedPolicies.size());
    }

    @Test
    public void testGetPoliciesByPolicyHolderName() {
        List<Policy> policies = new ArrayList<>();
        policies.add(new Policy());
        policies.add(new Policy());

        when(policyRepository.findByPolicyHolderName("John Doe")).thenReturn(policies);

        List<Policy> retrievedPolicies = policyService.getPoliciesByPolicyHolderName("John Doe");

        assertNotNull(retrievedPolicies);
        assertEquals(2, retrievedPolicies.size());
    }

    @Test
    public void testGetPolicyByPolicyNumber() {
        Policy policy = new Policy();
        policy.setPolicyNumber("POL123");
        policy.setPolicyHolderName("John Doe");

        when(policyRepository.findByPolicyNumber("POL123")).thenReturn(Optional.of(policy));

        Policy retrievedPolicy = policyService.getPolicyByPolicyNumber("POL123");

        assertNotNull(retrievedPolicy);
        assertEquals("John Doe", retrievedPolicy.getPolicyHolderName());
    }

    @Test
    public void testUpdatePolicy() {
        Policy policy = new Policy();
        policy.setId(1L);
        policy.setPolicyNumber("POL123");
        policy.setPolicyHolderName("John Doe");

        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));
        when(policyRepository.save(policy)).thenReturn(policy);

        Policy updatedPolicy = policyService.updatePolicy(policy);

        assertNotNull(updatedPolicy);
        assertEquals("POL123", updatedPolicy.getPolicyNumber());
    }

    @Test
    public void testDeletePolicy() {
        Policy policy = new Policy();
        policy.setId(1L);

        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));

        policyService.deletePolicy(1L);

        // Verify that the policy is deleted
    }
}