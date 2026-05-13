package com.showgo.dto;

/**
 * DTO to return only name and id for performers
 * when adding performers to events
 */
public class PerformerDto {
    public int id;
    public String name;

    public PerformerDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
