package com.showgo.entity;

import com.showgo.persistence.Identifiable;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

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

    /**
     * Instantiates a new Performer.
     */
    public Performer() {
    }

    /**
     * Instantiates a new Performer.
     *
     * @param name the name
     */
    public Performer(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Performer performer = (Performer) o;
        return id == performer.id && Objects.equals(name, performer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
