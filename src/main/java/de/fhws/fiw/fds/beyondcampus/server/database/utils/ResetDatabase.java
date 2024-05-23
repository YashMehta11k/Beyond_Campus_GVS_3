package de.fhws.fiw.fds.beyondcampus.server.database.utils;

import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;

public class ResetDatabase {

    public static void resetAll(){
        DaoFactory.getInstance().getPartnerUniDao().resetDatabase();
    }
}
