package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.Start;

public interface PartnerUniUri {

    String PATH_ELEMENT="partnerunis";
    String REL_PATH= Start.CONTEXT_PATH+"/api/"+PATH_ELEMENT;
    String REL_PATH_ID=REL_PATH+"/{id}";
}
