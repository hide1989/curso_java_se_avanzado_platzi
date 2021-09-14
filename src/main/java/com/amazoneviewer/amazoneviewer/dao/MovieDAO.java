package com.amazoneviewer.amazoneviewer.dao;

import com.amazoneviewer.db.IDBConnection;
import com.amazoneviewer.model.Movie;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static com.amazoneviewer.db.DataBase.*;

public interface MovieDAO extends IDBConnection {

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
                movie.setViewed(getMovieViewed(preparedStatement, connection, rs.getInt(TMOVIE_ID)));
                movies.add(movie);
            }
        }catch (SQLException e){
            System.out.println(e);
        }finally{

        }
        return movies;
    }
    default Movie setMovieViewed(Movie movie){

        try(Connection connection = connectToDB()){
            Statement statement = connection.createStatement();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(LocalDateTime.now().format(formatter));
            String query = "INSERT INTO "+ TVIEWED + " ("+TVIEWED_IDMATERIAL+", "+ TVIEWED_IDELEMENT +", "+ TVIEWED_IDUSUARIO+", "+TVIEWED_DATE+" )" +
                            "VALUES("+ID_TMATERIAL[0]+ ", " +movie.getId() +", " + TUSER_IDUSUARIO+", "+ "'"+LocalDateTime.now().format(formatter)+"'" + ")";
            System.out.println(query);
            if(statement.executeUpdate(query) > 0){
                System.out.println("Se marco en visto");
            }else{
                System.out.println("ERROR: No se marco en visto");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

        }
        return movie;
    }

    private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int id_movie){
        boolean viewed = false;
        String query = "SELECT * FROM "+ TVIEWED + " WHERE " + TVIEWED_IDMATERIAL + "= ?" + " AND " + TVIEWED_IDELEMENT + "= ?" + " AND " + TVIEWED_IDUSUARIO + "= ?";
        System.out.println(query);
        ResultSet rs = null;

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID_TMATERIAL[0]);
            preparedStatement.setInt(2, id_movie);
            preparedStatement.setInt(3, TUSER_IDUSUARIO);

            rs = preparedStatement.executeQuery();
            viewed = rs.next();


        }catch (SQLException e){
            e.printStackTrace();
        }

        return viewed;
    }

    default List<Movie> moviesByDate(LocalDateTime date){
        List<Movie> listMoviesByDate = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateFormated = date.format(formatter);
        String query = "SELECT * FROM "+TMOVIE+" INNER JOIN "+TVIEWED+" ON "+ TMOVIE+"."+TMOVIE_ID+" = "+TVIEWED_IDELEMENT+" WHERE CAST("+TVIEWED_DATE+" AS date)= '"+dateFormated+"'";
        System.out.println(query);
        try(Connection connection = connectToDB()) {
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
                listMoviesByDate.add(movie);
            }
        }catch (SQLException e){

        }finally {

        }

        return listMoviesByDate;

    }

}
