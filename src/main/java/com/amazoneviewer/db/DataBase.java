package com.amazoneviewer.db;

public class DataBase {

    public static final String URL = "jdbc:mysql://localhost:3307/";
    public static final String DB = "amazonviewer";
    public static final String USER = "amazonviewer";
    public static final String PASSWORD = "amazonviewer";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String TMOVIE = "movie";
    public static final String TMOVIE_ID = "id";
    public static final String TMOVIE_TITLE = "title";
    public static final String TMOVIE_GENRE = "genre";
    public static final String TMOVIE_CREATOR = "creator";
    public static final String TMOVIE_DURATION = "duration";
    public static final String TMOVIE_YEAR = "year";

    public static final String TMATERIAL = "year";
    public static final int[] ID_TMATERIAL = {1,2,3,4,5};

    public static final String TVIEWED = "viewed";
    public static final String TVIEWED_IDMATERIAL = "id_material";
    public static final String TVIEWED_IDELEMENT = "id_element";
    public static final String TVIEWED_IDUSUARIO = "id_user";






}
