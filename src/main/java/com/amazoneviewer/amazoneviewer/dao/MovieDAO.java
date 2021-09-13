package com.amazoneviewer.amazoneviewer.dao;

import com.amazoneviewer.db.IDBConnection;
import com.amazoneviewer.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.amazoneviewer.db.DataBase.*;

public interface MovieDAO extends IDBConnection {

    default Movie setMovieViewed(Movie movie){
        return movie;
    }

    default List<Movie> read(){
        List<Movie> movies = new ArrayList<>();
        try(Connection connection = connectToDB()){
            String query = "SELECT * FROM "+ TMOVIE;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Movie movie = new Movie(
                    rs.getString(TMOVIE_TITLE),
                    rs.getString(TMOVIE_GENRE),
                    rs.getString(TMOVIE_CREATOR),
                    rs.getInt(TMOVIE_DURATION),
                    rs.getShort(TMOVIE_YEAR)
                );
                movie.setId(rs.getInt(TMOVIE_ID));
                movies.add(movie);
            }
        }catch (SQLException e){
            System.out.println(e);
        }finally{

        }
        return movies;
    }

    private boolean getMovieViewed(){
        return false;
    }
}
