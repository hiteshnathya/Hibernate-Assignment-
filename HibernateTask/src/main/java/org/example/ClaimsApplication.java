package org.example;

import com.claims.entity.Claim;
import com.claims.entity.Policy;
import com.claims.service.ClaimService;
import com.claims.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClaimsApplication implements CommandLineRunner {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private PolicyService policyService;

    public static void main(String[] args) {
        SpringApplication.run(ClaimsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a policy
        Policy policy = new Policy();
        policy.setPolicyNumber("POL-001");
        policy.setPolicyHolderName("John Doe");
        policy.setPolicyEffectiveDate(new Date());
        policy.setPolicyExpirationDate(new Date());
        policy.setPremiumAmount(100.0);
        policyService.savePolicy(policy);

        // Create a claim
        Claim claim = new Claim();
        claim.setClaimDate(new Date());
        claim.setClaimType("Accident");
        claim.setClaimStatus("Open");
        claim.setDescription("Car accident claim");
        claim.setPolicy(policy);
        claimService.saveClaim(claim);
    }
}