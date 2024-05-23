package de.fhws.fiw.fds.beyondcampus.server.database.utils;

import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;

public class InitializeDatabase {

    public static void initialDBWithRelations(){
        DaoFactory.getInstance().getPartnerUniModuleDao().initializeDatabase();
    }
}
