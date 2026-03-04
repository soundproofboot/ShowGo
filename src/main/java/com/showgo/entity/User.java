package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "user")
public class User implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PerformerFollow> performerFollows = new HashSet<>();
    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Gets performer follows
     *
     * @return PerformerFollows related to this user
     */
    public Set<PerformerFollow> getPerformerFollows() {
        return performerFollows;
    }

    /**
     * Sets performer follows
     *
     * @param performerFollows associated with this user
     */
    public void setPerformerFollows(Set<PerformerFollow> performerFollows) {
        this.performerFollows = performerFollows;
    }

    /**
     * Follow performer
     *
     * @param performer the performer to follow
     */
    public void addPerformerFollow(Performer performer) {
        PerformerFollow performerFollow = new PerformerFollow(this, performer);
        performerFollows.add(performerFollow);
        performer.getFollowers().add(performerFollow);
    }

    /**
     * Remove a performer follow
     *
     * @param performer the performer to stop following
     */
    public void removePerformerFollow(Performer performer) {
        for (Iterator<PerformerFollow> iterator = performerFollows.iterator();
             iterator.hasNext(); ) {
            PerformerFollow performerFollow = iterator.next();

            if (performerFollow.getPerformer().equals(performer) &&
                    performerFollow.getUser().equals(this) ) {
                iterator.remove();
                performerFollow.getPerformer().getFollowers().remove(performerFollow);
                performerFollow.setPerformer(null);
                performerFollow.setUser(null);
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", performerFollows=" + performerFollows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
