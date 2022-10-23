package com.example.activitynavegation.model;

import java.io.Serializable;

public class Photographer implements Serializable {

    private String id;
    private String name;
    private String description;

    public Photographer() {
    }

    public Photographer(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
