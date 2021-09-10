package com.amazoneviewer.model;

import java.util.Date;

public interface IVisualizable {

    /**
     * Este método camtura el tiempo exacto de visualización
     * @param dateI Es un objeto de  {@code Date} con el tiempo de inicio exacto
     * @return  Devulve la fecha y hora de captura
     * */
    Date startToSee(Date dateI);

    /**
     * Este método camtura el tiempo exacto de inicio y final de visualización
     * @param dateI Es un objeto de  {@code Date} con el tiempo de inicio exacto
     * @param dateF Es un objeto de  {@code Date} con el tiempo de final exacto
     * */
    void stopToSee(Date dateI, Date dateF);

}
