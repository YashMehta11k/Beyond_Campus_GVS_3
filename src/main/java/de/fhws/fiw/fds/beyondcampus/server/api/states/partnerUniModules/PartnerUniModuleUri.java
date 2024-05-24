package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules;

import de.fhws.fiw.fds.beyondcampus.Start;

public interface PartnerUniModuleUri {

    String SHOW_ALL_PARAMETER = "showAll";
    String PATH_ELEMENT = "partnerunis/{id}/modules";

    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
    String REL_PATH_SHOW_ONLY_LINKED = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
    String REL_PATH_ID = REL_PATH + "/{id}";
}
