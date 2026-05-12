package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
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

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "performer", fetch = FetchType.EAGER)
    private Set<PerformerFollow> followers = new HashSet<>();

    @OneToMany(mappedBy = "performer", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
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
     * @param name        the name
     * @param description the description
     * @param genre       the genre
     * @param user        the user that manages this performer
     */
    public Performer(String name, String description, String genre, User user) {
        this.name = name;
        this.user = user;
        this.description = description;
        this.genre = genre;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
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

    /**
     * Gets events.
     *
     * @return the events
     */
    public Set<EventPerformer> getEvents() {
        return events;
    }

    /**
     * Sets events.
     *
     * @param events the events
     */
    public void setEvents(Set<EventPerformer> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", manager=" + user.getUsername() + '\'' +
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
