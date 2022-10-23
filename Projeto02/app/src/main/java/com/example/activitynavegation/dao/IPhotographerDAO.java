package com.example.activitynavegation.dao;

import com.example.activitynavegation.model.Photographer;

import java.util.List;

public interface IPhotographerDAO {
    void createPhotographersMock();
    List<Photographer> getPhotographers();
    Photographer getPhotographer(Photographer photographer);
    boolean addPhotographer(Photographer photographer);
    boolean editPhotographer(Photographer photographerEdited, Photographer oldPhotographer);
    void removePhotographer(int id);
}
