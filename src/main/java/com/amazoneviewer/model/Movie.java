package com.amazoneviewer.model;

import com.amazoneviewer.amazoneviewer.dao.MovieDAO;

import java.util.Date;
import java.util.List;

/**
 *Hereda de {@link Film}
 * Implemena de {@link IVisualizable}
 * */
public class Movie extends Film implements IVisualizable, MovieDAO {

    private int id;
    private int timeViewed;


    public Movie(String title, String genre, String creator, int duration, short year) {
        super(title, genre, creator, duration);
        setYear(year);
    }

    public Movie(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeViewed() {
        return timeViewed;
    }
    public void setTimeViewed(int timeViewed) {
        this.timeViewed = timeViewed;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return  "\n :: MOVIE ::" +
                "\n Title: " + getTitle() +
                "\n Genero: " + getGenre() +
                "\n Year: " + getYear() +
                "\n Creator: " + getCreator() +
                "\n Duration: " + getDuration();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Date startToSee(Date dateI) {
        // TODO Auto-generated method stub
        return dateI;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void stopToSee(Date dateI, Date dateF) {
        // TODO Auto-generated method stub

        if (dateF.getTime() > dateI.getTime()) {
            setTimeViewed((int)(dateF.getTime() - dateI.getTime()));
        }else {
            setTimeViewed(0);
        }
    }

    public static List<Movie> makeMoviesList() {
        Movie movie = new Movie();
        return movie.read();
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void view() {
        setViewed(true);
        Date dateI = startToSee(new Date());
        Movie movie = new Movie();
        movie.setMovieViewed(this);

        //Termine de verla
        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Viste: " + toString());
        System.out.println("Por: " + getTimeViewed() + " milisegundos");
    }

}
