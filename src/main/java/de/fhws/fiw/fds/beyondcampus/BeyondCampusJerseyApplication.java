package de.fhws.fiw.fds.beyondcampus;

import de.fhws.fiw.fds.sutton.server.api.AbstractJerseyApplication;
//Dispatcher,PartnerUni ,Modules jersey services to be added
//import de.fhws.fiw.fds.beyondcampus.server.api.services.DisptacherJerseyService;
//import de.fhws.fiw.fds.beyondcampus.server.api.services.PartnerUniJerseyService;
//import de.fhws.fiw.fds.beyondcampus.server.api.services.ModulesJerseyService;
import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class BeyondCampusJerseyApplication extends AbstractJerseyApplication{

    @Override
    protected Set<Class<?>> getServiceClasses(){
        final Set<Class<?>> returnValue = new HashSet<>();

        //to be returned
        //returnValue.add(PartnerUniJerseyService.class);
        //returnValue.add(ModulesJerseyService.class);
        //returnValue.add(DispatcherJerseyService.class);

        return returnValue;
    }
}
