package de.fhws.fiw.fds.beyondcampus.server.api.comparators;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;

import java.util.Comparator;

public class PartnerUniModuleComparator {

    public static Comparator<Module> by(final String orderAttribute){
        switch (orderAttribute){
            case "+credits":
                return byCredits();
            case "-credits":
                return byCredits();
            default:
                return byId();
        }
    }
    public static Comparator<Module> byId(){
        return Comparator.comparing(Module::getId);
    }

    public static Comparator<Module> byCredits(){
        return Comparator.comparing(Module::getNoOfCredits).thenComparing(Module::getModName);
    }
}
