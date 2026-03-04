package com.showgo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Join table representing performers followed by a user
 */
@Entity(name = "PerformerFollow")
@Table(name = "performer_follow")
public class PerformerFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "performer_id", referencedColumnName = "id")
    private Performer performer;

}
