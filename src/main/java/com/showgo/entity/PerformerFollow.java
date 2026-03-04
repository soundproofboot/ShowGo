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

    public PerformerFollow() {
    }

    public PerformerFollow(User user, Performer performer) {
        this.user = user;
        this.performer = performer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Performer getPerformer() {
        return performer;
    }

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
