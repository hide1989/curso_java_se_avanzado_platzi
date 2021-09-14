package com.amazoneviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.amazoneviewer.db.DataBase.*;

public interface IDBConnection {

    default Connection connectToDB(){
        Connection connection = null;

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL+DB, USER, PASSWORD);
            if (connection != null){
                System.out.println("Se establecio la coneccion correctamente!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return connection;
        }
    }
}
