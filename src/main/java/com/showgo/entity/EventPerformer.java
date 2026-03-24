package com.showgo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

/**
 * Join table representing performers in event lineup
 */
@Entity(name = "EventPerformer")
@Table(name = "event_performer")
public class EventPerformer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "performer_id", referencedColumnName = "id")
    private Performer performer;

    /**
     * Instantiates a new Event performer.
     */
    public EventPerformer() {
    }

    /**
     * Instantiates a new Event performer.
     *
     * @param event     the event
     * @param performer the performer
     */
    public EventPerformer(Event event, Performer performer) {
        this.event = event;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventPerformer that = (EventPerformer) o;
        return Objects.equals(event, that.event) && Objects.equals(performer, that.performer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, performer);
    }

    @Override
    public String toString() {
        return "EventPerformer{" +
                "id=" + id +
                ", event=" + event +
                ", performer=" + performer +
                '}';
    }
}
