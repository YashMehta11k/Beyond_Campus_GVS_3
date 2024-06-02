package de.fhws.fiw.fds.beyondcampus.server.api.comparators;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;

import java.util.Comparator;

public class PartnerUniComparator {

    public static Comparator<PartnerUni> by(final String orderAttribute){
        switch (orderAttribute){
            case "+uniname":
                return byNames();
            case "-uniname":
                return byNames().reversed();
            case "closestSem":
                return bySem();
            default:
                return byId();
        }
    }
    public static Comparator<PartnerUni> byId(){
        return Comparator.comparing(PartnerUni::getId);
    }

    public static Comparator<PartnerUni> byNames(){
        return Comparator.comparing(PartnerUni::getUniName).thenComparing(PartnerUni::getUniCountry);
    }

    public static Comparator<PartnerUni> bySem(){
        return Comparator.comparing(PartnerUni::getUpcomingAutumnSem).thenComparing(PartnerUni::getUpcomingSpringSem);
    }
}
