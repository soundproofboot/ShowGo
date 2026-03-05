package com.showgo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * Join table representing performers followed by a user
 */
@Entity(name = "PerformerFollow")
@Table(name = "performer_follow")
public class PerformerFollow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "performer_id", referencedColumnName = "id")
    private Performer performer;

    /**
     * Instantiates a new Performer follow.
     */
    public PerformerFollow() {
    }

    /**
     * Instantiates a new Performer follow.
     *
     * @param user      the user
     * @param performer the performer
     */
    public PerformerFollow(User user, Performer performer) {
        this.user = user;
        this.performer = performer;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets performer.
     *
     * @return the performer
     */
    public Performer getPerformer() {
        return performer;
    }

    /**
     * Sets performer.
     *
     * @param performer the performer
     */
    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    @Override
    public String toString() {
        return "PerformerFollow{" +
                "id=" + id +
//                ", user=" + user +
                ", performer=" + performer +
                '}';
    }
}
