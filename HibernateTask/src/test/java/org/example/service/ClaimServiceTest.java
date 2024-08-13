package org.example.service;
import com.claims.entity.Claim;
import com.claims.entity.Policy;
import com.claims.repository.ClaimRepository;
import com.claims.repository.PolicyRepository;
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
public class ClaimServiceTest {

    @InjectMocks
    private ClaimService claimService;

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private PolicyRepository policyRepository;

    @Before
    public void setup() {
        // Initialize test data
    }

    @Test
    public void testCreateClaim() {
        Policy policy = new Policy();
        policy.setId(1L);
        policy.setPolicyNumber("POL123");

        Claim claim = new Claim();
        claim.setClaimDate(new Date());
        claim.setClaimType("Accident");
        claim.setClaimStatus("Pending");
        claim.setDescription("Test claim");
        claim.setPolicy(policy);

        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));
        when(claimRepository.save(claim)).thenReturn(claim);

        Claim createdClaim = claimService.createClaim(claim);

        assertNotNull(createdClaim);
        assertEquals("Accident", createdClaim.getClaimType());
    }

    @Test
    public void testGetClaimById() {
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setClaimDate(new Date());
        claim.setClaimType("Accident");
        claim.setClaimStatus("Pending");
        claim.setDescription("Test claim");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

        Claim retrievedClaim = claimService.getClaimById(1L);

        assertNotNull(retrievedClaim);
        assertEquals("Accident", retrievedClaim.getClaimType());
    }

    @Test
    public void testGetAllClaims() {
        List<Claim> claims = new ArrayList<>();
        claims.add(new Claim());
        claims.add(new Claim());

        when(claimRepository.findAll()).thenReturn(claims);

        List<Claim> retrievedClaims = claimService.getAllClaims();

        assertNotNull(retrievedClaims);
        assertEquals(2, retrievedClaims.size());
    }

    @Test
    public void testUpdateClaim() {
        Claim claim = new Claim();
        claim.setId(1L);
        claim.setClaimDate(new Date());
        claim.setClaimType("Accident");
        claim.setClaimStatus("Pending");
        claim.setDescription("Test claim");

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));
        when(claimRepository.save(claim)).thenReturn(claim);

        Claim updatedClaim = claimService.updateClaim(claim);

        assertNotNull(updatedClaim);
        assertEquals("Accident", updatedClaim.getClaimType());
    }

    @Test
    public void testDeleteClaim() {
        Claim claim = new Claim();
        claim.setId(1L);

        when(claimRepository.findById(1L)).thenReturn(Optional.of(claim));

        claimService.deleteClaim(1L);

        // Verify that the claim is deleted
    }
}