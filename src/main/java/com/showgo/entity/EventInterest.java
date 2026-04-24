package com.showgo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

/**
 * Join table representing user interest in events
 */
@Entity(name = "EventInterest")
@Table(name = "event_interest")
public class EventInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "event_id", referencedColumnName = "id")
    private Event event;

    /**
     * Instantiate a new Event interest
     */
    public EventInterest() {}

    /**
     * Instantiates a new Event interest.
     *
     * @param user  the user
     * @param event the event
     */
    public EventInterest(User user, Event event) {
        this.user = user;
        this.event = event;
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
     * Gets event.
     *
     * @return the event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets event.
     *
     * @param event the event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventInterest that = (EventInterest) o;
        return id == that.id && Objects.equals(user, that.user) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, event);
    }

    @Override
    public String toString() {
        return "EventInterest{" +
                "id=" + id +
//                ", user=" + user +
                ", event=" + event +
                '}';
    }
}
