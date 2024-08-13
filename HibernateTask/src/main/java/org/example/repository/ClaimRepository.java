package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.claims.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    /**
     * Find a claim by its claim number
     *
     * @param claimNumber the claim number
     * @return the claim
     */
    @Query("SELECT c FROM Claim c WHERE c.claimNumber = :claimNumber")
    Claim findByClaimNumber(@Param("claimNumber") String claimNumber);

    /**
     * Find all claims for a given policy
     *
     * @param policyId the policy ID
     * @return a list of claims
     */
    @Query("SELECT c FROM Claim c WHERE c.policy.id = :policyId")
    List<Claim> findByPolicyId(@Param("policyId") Long policyId);

    /**
     * Find all claims for a given customer
     *
     * @param customerId the customer ID
     * @return a list of claims
     */
    @Query("SELECT c FROM Claim c WHERE c.policy.customer.id = :customerId")
    List<Claim> findByCustomerId(@Param("customerId") Long customerId);

    /**
     * Find all claims with a given status
     *
     * @param status the claim status
     * @return a list of claims
     */
    @Query("SELECT c FROM Claim c WHERE c.status = :status")
    List<Claim> findByStatus(@Param("status") String status);

    /**
     * Update the claim status
     *
     * @param claimNumber the claim number
     * @param status the new claim status
     */
    @Modifying
    @Transactional
    @Query("UPDATE Claim c SET c.status = :status WHERE c.claimNumber = :claimNumber")
    void updateClaimStatus(@Param("claimNumber") String claimNumber, @Param("status") String status);

    /**
     * Delete a claim by its claim number
     *
     * @param claimNumber the claim number
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Claim c WHERE c.claimNumber = :claimNumber")
    void deleteByClaimNumber(@Param("claimNumber") String claimNumber);
}