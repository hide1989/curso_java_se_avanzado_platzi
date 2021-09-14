package com.amazoneviewer;

import com.amazoneviewer.model.*;
import com.amazoneviewer.util.AmazonUtil;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * AmazoneViewer
 * Es un programa de consola que simula la visualización de peliculas, series y capitulos,
 * tambien leer books y magazines.
 * te permite generar reportes con fecha y año del día
 *
 *  Como regla tiene que todos los elementos pueden ser visualizados excepto los magazine
 *
 * @author Platzi
 * @version 1.1
 * @since 2021
 *
 * */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        showMenu();

    }

    public static void showMenu() throws FileNotFoundException {
        int exit = 0;
        do {

            System.out.println("BIENVENIDOS AMAZON VIEWER");
            System.out.println("");
            System.out.println("Selecciona el número de la opción deseada");
            System.out.println("1. Movies");
            System.out.println("2. Series");
            System.out.println("3. Books");
            System.out.println("4. Magazines");
            System.out.println("5. Report");
            System.out.println("6. Report Today");
            System.out.println("0. Exit");

            //Leer la respuesta del usuario
            int response = AmazonUtil.validateUserResponseMenu(0, 6);

            switch (response) {
                case 0:
                    //salir
                    exit = 0;
                    break;
                case 1:
                    showMovies();
                    break;
                case 2:
                    showSeries();
                    break;
                case 3:
                    showBooks();
                    break;
                case 4:
                    showMagazines();
                    break;
                case 5:
                    makeReport();
                    exit = 1;
                    break;
                case 6:
                    //Date date = new Date();
                    makeReport(LocalDateTime.now());
                    exit = 1;
                    break;

                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    System.out.println();
                    exit = 1;
                    break;
            }


        }while(exit != 0);
    }

    static List<Movie> movies = new ArrayList<>();
    public static void showMovies() throws FileNotFoundException {
        movies = Movie.makeMoviesList();
        int exit = 1;

        do {
            System.out.println();
            System.out.println(":: MOVIES ::");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            movies.forEach(m -> System.out.println(atomicInteger.getAndIncrement() + ". " + m.getTitle() + " Visto: " + m.isViewed()));

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            int response = AmazonUtil.validateUserResponseMenu(0, movies.size());

            if(response == 0) {
                exit = 0;
                showMenu();
                break;
            }
            if (response > 0) {
                Movie movieSelected = movies.get(response-1);
                movieSelected.view();
            }


        }while(exit !=0);

    }
    static ArrayList<Serie> series = Serie.makeSeriesList();
    public static void showSeries() throws FileNotFoundException {
        int exit = 1;

        do {
            System.out.println();
            System.out.println(":: SERIES ::");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            series.forEach(s -> System.out.println(atomicInteger.getAndIncrement() + ". " + s.getTitle() + " Visto: " + s.isViewed()));

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            int response = AmazonUtil.validateUserResponseMenu(0, series.size());

            if(response == 0) {
                exit = 0;
                showMenu();
            }

            if(response > 0) {
                showChapters(series.get(response-1).getChapters());
            }


        }while(exit !=0);
    }

    public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
        int exit = 1;

        do {
            System.out.println();
            System.out.println(":: CHAPTERS ::");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            chaptersOfSerieSelected.forEach(c -> System.out.println(atomicInteger.getAndIncrement() + ". " + c.getTitle() + " Visto: " + c.isViewed()));

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            int response = AmazonUtil.validateUserResponseMenu(0, chaptersOfSerieSelected.size());

            if(response == 0) {
                exit = 0;
            }


            if(response > 0) {
                Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
                chapterSelected.view();
            }
        }while(exit !=0);
    }

    static ArrayList<Book> books= Book.makeBookList();
    public static void showBooks() throws FileNotFoundException {
        int exit = 1;

        do {
            System.out.println();
            System.out.println(":: BOOKS ::");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            books.forEach(b -> System.out.println(atomicInteger.getAndIncrement() + ". " + b.getTitle() + " Leído: " + b.isReaded()));

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            int response = AmazonUtil.validateUserResponseMenu(0, books.size());

            if(response == 0) {
                exit = 0;
                showMenu();
            }

            if(response > 0) {
                Book bookSelected = books.get(response-1);
                bookSelected.view();
            }

        }while(exit !=0);
    }

    public static void showMagazines() throws FileNotFoundException {
        List<Magazine> magazines = Magazine.makeMagazineList();
        int exit = 0;
        do {
            System.out.println();
            System.out.println(":: MAGAZINES ::");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            magazines.forEach(z -> System.out.println(atomicInteger.getAndIncrement()+ ". " + z.getTitle()));

            for (int i = 0; i < magazines.size(); i++) { //1. Book 1
                System.out.println(i+1 + ". " + magazines.get(i).getTitle());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            //Leer Respuesta usuario
            int response = AmazonUtil.validateUserResponseMenu(0, 0);

            if(response == 0) {
                exit = 0;
                showMenu();
            }


        }while(exit !=0);
    }

    public static void makeReport() throws FileNotFoundException {

        Report report = new Report();
        report.setNameFile("reporte");
        report.setExtension("txt");
        report.setTitle(":: VISTOS ::");
        final StringBuilder contentReport = new StringBuilder();

        movies.stream().filter(movie -> movie.getIsViewed()).forEach(m -> contentReport.append(m.toString()).append("\n"));

        /*
        for (Movie movie : movies) {
            if (movie.getIsViewed()) {
                contentReport += movie.toString() + "\n";

            }
        }
         */

        Consumer<Serie> seriesEach = s -> {
            List<Chapter> chapters = s.getChapters();
            chapters.stream().filter(c -> c.getIsViewed()).forEach(c -> contentReport.append(c.toString()).append("\n"));
        };
        series.forEach(seriesEach);

        /*
        for (Serie serie : series) {
            ArrayList<Chapter> chapters = serie.getChapters();
            for (Chapter chapter : chapters) {
                if (chapter.getIsViewed()) {
                    contentReport += chapter.toString() + "\n";

                }
            }
        }

         */

        books.stream().filter(b -> b.getIsReaded()).forEach(b -> contentReport.append(b.toString()).append("\n"));

        /*
        for (Book book : books) {
            if (book.getIsReaded()) {
                contentReport += book.toString() + "\n";

            }
        }

         */

        report.setContent(contentReport.toString());
        report.makeReport();
        System.out.println("Reporte Generado");
        System.out.println();
    }

    public static void makeReport(LocalDateTime date) throws FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        Report report = new Report();

        report.setNameFile("reporte" + dateString);
        report.setExtension("txt");
        report.setTitle(":: VISTOS ::");

        String contentReport = "Date: " + dateString + "\n\n\n";
        List<Movie> moviesByDate = new Movie().moviesByDate(date);

        for (Movie movie : moviesByDate) {
            contentReport += movie.toString() + "\n";
        }

        for (Serie serie : series) {
            ArrayList<Chapter> chapters = serie.getChapters();
            for (Chapter chapter : chapters) {
                if (chapter.getIsViewed()) {
                    contentReport += chapter.toString() + "\n";

                }
            }
        }

        for (Book book : books) {
            if (book.getIsReaded()) {
                contentReport += book.toString() + "\n";

            }
        }
        report.setContent(contentReport);
        report.makeReport();

        System.out.println("Reporte Generado");
        System.out.println();
    }

}
