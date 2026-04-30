package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
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

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "event_start")
    private LocalDateTime eventStart;

    @ManyToOne
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<EventPerformer> performers = new HashSet<>();

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Set<EventInterest> eventInterests = new HashSet<>();

    /**
     * Instantiates a new Event.
     */
    public Event() {
    }

    /**
     * Instantiates a new Event.
     *
     * @param title      the title
     * @param venue      the venue
     * @param eventStart the event start
     */
    public Event(String title, Venue venue, LocalDateTime eventStart) {
        this.title = title;
        this.venue = venue;
        this.eventStart = eventStart;
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
     * Gets created at.
     *
     * @return the created at
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets event start.
     *
     * @return the event start
     */
    public LocalDateTime getEventStart() {
        return eventStart;
    }

    /**
     * Sets event start.
     *
     * @param eventStart the event start
     */
    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, yyyy");
        return eventStart.format(formatter);
    }

    public String getTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return eventStart.format(formatter);
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

    /**
     * Add event performer.
     *
     * @param performer the performer
     */
    public void addEventPerformer(Performer performer) {
        EventPerformer eventPerformer = new EventPerformer(this, performer);
        performers.add(eventPerformer);
        performer.getEvents().add(eventPerformer);
    }

    /**
     * Remove event performer.
     *
     * @param performer the performer
     */
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

    /**
     * Gets event interests.
     *
     * @return the event interests
     */
    public Set<EventInterest> getEventInterests() {
        return eventInterests;
    }

    /**
     * Sets event interests.
     *
     * @param eventInterests the event interests
     */
    public void setEventInterests(Set<EventInterest> eventInterests) {
        this.eventInterests = eventInterests;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", venue=" + venue +
                ", createdAt=" + createdAt +
                ", eventStart=" + eventStart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id != 0 && id == event.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
