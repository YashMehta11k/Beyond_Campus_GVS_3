package de.fhws.fiw.fds.beyondcampus.server.database;

import de.fhws.fiw.fds.beyondcampus.server.database.inmemory.PartnerUniStorage;
import de.fhws.fiw.fds.beyondcampus.server.database.inmemory.PartnerUniModuleStorage;
import de.fhws.fiw.fds.beyondcampus.server.database.inmemory.ModuleStorage;

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance(){
        if(INSTANCE==null){
            INSTANCE=new DaoFactory();
        }
        return INSTANCE;
    }

    private final PartnerUniDao partnerUniDao;
    private final ModuleDao moduleDao;
    private final PartnerUniModuleDao partnerUniModuleDao;

    private DaoFactory(){
        this.partnerUniDao=new PartnerUniStorage();
        this.moduleDao=new ModuleStorage();
        this.partnerUniModuleDao=new PartnerUniModuleStorage();
    }

    public PartnerUniDao getPartnerUniDao(){
        return this.partnerUniDao;
    }

    public ModuleDao getModuleDao(){
        return this.moduleDao;
    }

    public PartnerUniModuleDao getPartnerUniModuleDao(){
        return this.partnerUniModuleDao;
    }
}
