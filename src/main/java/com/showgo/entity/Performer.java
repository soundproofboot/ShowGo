package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Reperesents the Performer table
 */
@Entity(name = "Performer")
@Table(name = "performer")
public class Performer implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PerformerFollow> followers = new HashSet<>();

    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<EventPerformer> events = new HashSet<>();

    @ManyToOne
    private User user;

    /**
     * Instantiates a new Performer.
     */
    public Performer() {
    }

    /**
     * Instantiates a new Performer.
     *
     * @param name the name
     * @param user the user that manages this performer
     */
    public Performer(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets followers
     *
     * @return PerformerFollows related to this performer
     */
    public Set<PerformerFollow> getFollowers() {
        return followers;
    }

    /**
     * Sets followers
     *
     * @param followers PerformerFollows related to this performer
     */
    public void setFollowers(Set<PerformerFollow> followers) {
        this.followers = followers;
    }

    public Set<EventPerformer> getEvents() {
        return events;
    }

    public void setEvents(Set<EventPerformer> events) {
        this.events = events;
    }

    /**
     * Add follower
     *
     * @param user the follower to add
     */
    public void addFollower(User user) {
        PerformerFollow performerFollow = new PerformerFollow(user, this);
        followers.add(performerFollow);
        user.getPerformerFollows().add(performerFollow);
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager=" + user + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Performer performer = (Performer) o;
        return id != 0 && id == performer.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
