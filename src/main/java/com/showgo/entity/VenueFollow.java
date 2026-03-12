package com.showgo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

/**
 * Join table representing venues followed by a user
 */
@Entity(name = "VenueFollow")
@Table(name = "venue_follow")
public class VenueFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id")
    private Venue venue;

    /**
     * Instantiates a new Venue follow.
     */
    public VenueFollow() {
    }

    /**
     * Instantiates a new Venue follow.
     *
     * @param user  the user
     * @param venue the venue
     */
    public VenueFollow(User user, Venue venue) {
        this.user = user;
        this.venue = venue;
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
     * Gets venue.
     *
     * @return the venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Sets venue.
     *
     * @param venue the venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VenueFollow that = (VenueFollow) o;
        return Objects.equals(user, that.user) && Objects.equals(venue, that.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, venue);
    }

    @Override
    public String toString() {
        return "VenueFollow{" +
                "id=" + id +
//                ", user=" + user +
                ", venue=" + venue +
                '}';
    }
}
