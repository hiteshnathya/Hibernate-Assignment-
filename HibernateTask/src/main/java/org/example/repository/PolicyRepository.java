package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.claims.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    /**
     * Find a policy by its policy number
     *
     * @param policyNumber the policy number
     * @return the policy
     */
    @Query("SELECT p FROM Policy p WHERE p.policyNumber = :policyNumber")
    Policy findByPolicyNumber(@Param("policyNumber") String policyNumber);

    /**
     * Find all policies for a given customer ID
     *
     * @param customerId the customer ID
     * @return a list of policies
     */
    @Query("SELECT p FROM Policy p WHERE p.customer.id = :customerId")
    List<Policy> findByCustomerId(@Param("customerId") Long customerId);

    /**
     * Find all policies of a given type
     *
     * @param policyType the policy type
     * @return a list of policies
     */
    @Query("SELECT p FROM Policy p WHERE p.policyType = :policyType")
    List<Policy> findByPolicyType(@Param("policyType") String policyType);

    /**
     * Update the policy status
     *
     * @param policyNumber the policy number
     * @param policyStatus the new policy status
     */
    @Modifying
    @Transactional
    @Query("UPDATE Policy p SET p.policyStatus = :policyStatus WHERE p.policyNumber = :policyNumber")
    void updatePolicyStatus(@Param("policyNumber") String policyNumber, @Param("policyStatus") String policyStatus);

    /**
     * Delete a policy by its policy number
     *
     * @param policyNumber the policy number
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Policy p WHERE p.policyNumber = :policyNumber")
    void deleteByPolicyNumber(@Param("policyNumber") String policyNumber);

    /**
     * Find all policies that are active
     *
     * @return a list of active policies
     */
    @Query("SELECT p FROM Policy p WHERE p.policyStatus = 'ACTIVE'")
    List<Policy> findActivePolicies();

    /**
     * Find all policies that are inactive
     *
     * @return a list of inactive policies
     */
    @Query("SELECT p FROM Policy p WHERE p.policyStatus = 'INACTIVE'")
    List<Policy> findInactivePolicies();
}