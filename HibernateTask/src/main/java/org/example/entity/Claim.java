package com.claims.entity;

import org.jcp.xml.dsig.internal.dom.Policy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @Column(nullable = false)
    private Date claimDate;

    @Column(nullable = false)
    private String claimType;

    @Column(nullable = false)
    private String claimStatus;

    @Column
    private String description;

    // Getters and setters
}