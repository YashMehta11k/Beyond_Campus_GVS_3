package de.fhws.fiw.fds.beyondcampus;

import de.fhws.fiw.fds.sutton.server.api.AbstractJerseyApplication;

import de.fhws.fiw.fds.beyondcampus.server.api.services.DispatcherJerseyService;
import de.fhws.fiw.fds.beyondcampus.server.api.services.PartnerUniJerseyService;
import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class BeyondCampusJerseyApplication extends AbstractJerseyApplication{

    @Override
    protected Set<Class<?>> getServiceClasses(){
        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(PartnerUniJerseyService.class);
        returnValue.add(DispatcherJerseyService.class);

        return returnValue;
    }
}
