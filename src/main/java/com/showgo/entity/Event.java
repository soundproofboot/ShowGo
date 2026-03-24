package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Represents the event table
 */
@Entity(name = "Event")
@Table(name = "event")
public class Event implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<EventPerformer> performers = new HashSet<>();

    /**
     * Instantiates a new Event.
     */
    public Event() {
    }

    /**
     * Instantiates a new Event.
     *
     * @param title the title
     * @param venue the venue
     */
    public Event(String title, Venue venue) {
        this.title = title;
        this.venue = venue;
    }

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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * Gets performers.
     *
     * @return the performers
     */
    public Set<EventPerformer> getPerformers() {
        return performers;
    }

    /**
     * Sets performers.
     *
     * @param performers the performers
     */
    public void setPerformers(Set<EventPerformer> performers) {
        this.performers = performers;
    }

    public void addEventPerformer(Performer performer) {
        EventPerformer eventPerformer = new EventPerformer(this, performer);
        performers.add(eventPerformer);
        performer.getEvents().add(eventPerformer);
    }

    public void removeEventPerformer(Performer performer) {
        for (Iterator<EventPerformer> iterator = performers.iterator(); iterator.hasNext(); ) {
            EventPerformer eventPerformer = iterator.next();
            if (eventPerformer.getPerformer().equals(performer) && eventPerformer.getEvent().equals(this)) {
                iterator.remove();
                eventPerformer.getPerformer().getEvents().remove(eventPerformer);
                eventPerformer.setEvent(null);
                eventPerformer.setPerformer(null);
            }
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", venue=" + venue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(title, event.title) && Objects.equals(venue, event.venue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, venue);
    }
}
