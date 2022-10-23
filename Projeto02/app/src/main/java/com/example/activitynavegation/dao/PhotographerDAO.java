package com.example.activitynavegation.dao;

import android.content.Context;

import com.example.activitynavegation.model.Photographer;

import java.util.ArrayList;
import java.util.List;

public class PhotographerDAO implements IPhotographerDAO {
    private static Context context;
    private static PhotographerDAO photographerDAO = null;
    private List<Photographer> photographers = new ArrayList<>();

    private PhotographerDAO(Context context) {
        PhotographerDAO.context = context;
        createPhotographersMock();
    }

    public static IPhotographerDAO getInstance(Context context) {
        if (photographerDAO == null) {
            photographerDAO = new PhotographerDAO(context);
        }
        return photographerDAO;
    }

    @Override
    public void createPhotographersMock() {
        Photographer photographer = new Photographer("1", "Roberto", "Astrofotógrafo");
        photographers.add(photographer);

        photographer = new Photographer("2", "Letícia", "Fotógrafaf subaquática");
        photographers.add(photographer);

        photographer = new Photographer("3", "Leonel", "Fotógrafo de eventos");
        photographers.add(photographer);

        photographer = new Photographer("4", "Simone", "Fotógrafa de casamentos");
        photographers.add(photographer);

        photographer = new Photographer("5", "George", "Fotógrafo de insetos");
        photographers.add(photographer);
    }

    @Override
    public List<Photographer> getPhotographers() {
        return this.photographers;
    }

    @Override
    public Photographer getPhotographer(Photographer photographer) {
        return null;
    }

    @Override
    public boolean addPhotographer(Photographer photographer) {
        for (Photographer currentPhotographer : this.photographers) {
            if (currentPhotographer.getId().equals(photographer.getId())) {
                return false;
            }
        }

        this.photographers.add(photographer);
        return true;
    }

    @Override
    public boolean editPhotographer(Photographer photographerEdited, Photographer oldPhotographer) {
        this.photographers.set(this.photographers.indexOf(oldPhotographer), photographerEdited);
        return true;
    }

    @Override
    public void removePhotographer(int id) {
        this.photographers.remove(id);
    }

    public void setPhotographer(List<Photographer> photographers) {
        this.photographers = photographers;
    }
}
