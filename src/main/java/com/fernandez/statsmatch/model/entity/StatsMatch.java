package com.fernandez.statsmatch.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "STATSMATCH")
@Data
public class StatsMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matchId;
    private String categoryName;
    private String homeValue;
    private String awayValue;
    private String cuarto;
}

