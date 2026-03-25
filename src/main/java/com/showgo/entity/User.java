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

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<PerformerFollow> performerFollows = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<VenueFollow> venueFollows = new HashSet<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param email    the email
     * @param city     the city
     * @param state    the state
     */
    public User(String username, String email, String city, String state) {
        this.username = username;
        this.email = email;
        this.city = city;
        this.state = state;
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
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
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
     * Gets venue follows.
     *
     * @return the venue follows
     */
    public Set<VenueFollow> getVenueFollows() {
        return venueFollows;
    }

    /**
     * Sets venue follows.
     *
     * @param venueFollows the venue follows
     */
    public void setVenueFollows(Set<VenueFollow> venueFollows) {
        this.venueFollows = venueFollows;
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
        System.out.println("performerFollows size before: " + performerFollows.size());
        System.out.println("Looking for performer: " + performer.getName());

        for (Iterator<PerformerFollow> iterator = performerFollows.iterator();
             iterator.hasNext(); ) {
            PerformerFollow performerFollow = iterator.next();

            System.out.println("Checking PerformerFollow id=" + performerFollow.getId()
                    + " performer=" + performerFollow.getPerformer().getName()
                    + " user=" + performerFollow.getUser().getId());
            if (performerFollow.getPerformer().equals(performer) &&
                    performerFollow.getUser().equals(this) ) {
                System.out.println("Match found - removing");
                iterator.remove();
                System.out.println("followers size before remove: " + performerFollow.getPerformer().getFollowers().size());
                performerFollow.getPerformer().getFollowers().remove(performerFollow);
                System.out.println("followers size after remove: " + performerFollow.getPerformer().getFollowers().size());
                performerFollow.setPerformer(null);
                performerFollow.setUser(null);
            }
        }
        System.out.println("performerFollows size after: " + performerFollows.size());
    }

    /**
     * Follow venue
     *
     * @param venue the venue to follow
     */
    public void addVenueFollow(Venue venue) {
        VenueFollow venueFollow = new VenueFollow(this, venue);
        venueFollows.add(venueFollow);
        venue.getFollowers().add(venueFollow);
    }

    /**
     * Remove a venue follow
     *
     * @param venue the venue to stop following
     */
    public void removeVenueFollow(Venue venue) {
        System.out.println("venueFollows size before: " + venueFollows.size());
        System.out.println("Looking for venue: " + venue.getName());

        for (Iterator<VenueFollow> iterator = venueFollows.iterator();
             iterator.hasNext(); ) {
            VenueFollow venueFollow = iterator.next();

            System.out.println("Checking VenueFollow id=" + venueFollow.getId()
                    + " venue=" + venueFollow.getVenue().getName()
                    + " user=" + venueFollow.getUser().getId());
            System.out.println("venue equals: " + venueFollow.getVenue().equals(venue));
            System.out.println("user equals: " + venueFollow.getUser().equals(this));

            if (venueFollow.getVenue().equals(venue) &&
                    venueFollow.getUser().equals(this)) {
                System.out.println("Match found - removing");
                iterator.remove();
                System.out.println("followers size before remove: " + venueFollow.getVenue().getFollowers().size());
                venueFollow.getVenue().getFollowers().remove(venueFollow);
                System.out.println("followers size after remove: " + venueFollow.getVenue().getFollowers().size());
                venueFollow.setVenue(null);
                venueFollow.setUser(null);
            }
        }
        System.out.println("venueFollows size after: " + venueFollows.size());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
//                TODO are these causing an issue?
//                ", performerFollows=" + performerFollows +
//                ", venueFollows=" + venueFollows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
