package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

/**
 * Represents the venue table
 */
@Entity(name = "Venue")
@Table(name = "venue")
public class Venue implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<VenueFollow> followers = new HashSet<>();

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();

    /**
     * Instantiates a new Venue.
     */
    public Venue() {
    }

    /**
     * Instantiates a new Venue.
     *
     * @param name the name
     */
    public Venue(String name) {
        this.name = name;
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
     * @return VenueFollows related to this venue
     */
    public Set<VenueFollow> getFollowers() {
        return followers;
    }

    /**
     * Sets followers
     *
     * @param followers VenueFollows related to this venue
     */
    public void setFollowers(Set<VenueFollow> followers) {
        this.followers = followers;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Add follower
     *
     * @param user the follower to add
     */
    public void addFollower(User user) {
        VenueFollow venueFollow = new VenueFollow(user, this);
        followers.add(venueFollow);
        user.getVenueFollows().add(venueFollow);
    }

    /**
     * Add event
     * @param event the event
     */
    public void addEvent(Event event) {
        events.add(event);
        event.setVenue(this);
    }

    /**
     * Remove event
     *
     * @param event the event
     */
    public void removeEvent(Event event) {
        events.remove(event);
        event.setVenue(null);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return Objects.equals(name, venue.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
