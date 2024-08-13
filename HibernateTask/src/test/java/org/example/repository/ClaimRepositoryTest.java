package org.example.repository;
import com.claims.entity.Claim;
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
public class ClaimRepositoryTest {

    @Autowired
    private ClaimRepository claimRepository;

    @Before
    public void setup() {
        // Initialize test data
    }

    @Test
    public void testSaveClaim() {
        Claim claim = new Claim();
        claim.setClaimDate(new Date());
        claim.setClaimType("Accident");
        claim.setClaimStatus("Pending");
        claim.setDescription("Test claim");
        claim.setPolicy(new Policy());

        claimRepository.saveClaim(claim);

        assertNotNull(claim.getId());
    }

    @Test
    public void testGetClaimById() {
        Claim claim = claimRepository.getClaimById(1L);

        assertNotNull(claim);
        assertEquals("Accident", claim.getClaimType());
    }

    @Test
    public void testGetAllClaims() {
        List<Claim> claims = claimRepository.getAllClaims();

        assertNotNull(claims);
        assertEquals(1, claims.size());
    }

    @Test
    public void testDeleteClaim() {
        Claim claim = claimRepository.getClaimById(1L);

        claimRepository.deleteClaim(claim.getId());

        Claim deletedClaim = claimRepository.getClaimById(1L);

        assertNull(deletedClaim);
    }
}