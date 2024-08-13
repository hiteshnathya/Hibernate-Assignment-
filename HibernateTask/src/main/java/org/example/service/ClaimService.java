package com.claims.service;

import com.claims.entity.Claim;
import com.claims.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    public void saveClaim(Claim claim) {
        claimRepository.saveClaim(claim);
    }

    public Claim getClaimById(Long id) {
        return claimRepository.getClaimById(id);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.getAllClaims();
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteClaim(id);
    }
}