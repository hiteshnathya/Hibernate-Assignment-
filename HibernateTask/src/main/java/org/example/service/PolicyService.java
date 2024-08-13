package com.claims.service;

import com.claims.entity.Policy;
import com.claims.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public void savePolicy(Policy policy) {
        policyRepository.savePolicy(policy);
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.getPolicyById(id);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.getAllPolicies();
    }

    public void deletePolicy(Long id) {
        policyRepository.deletePolicy(id);
    }
}