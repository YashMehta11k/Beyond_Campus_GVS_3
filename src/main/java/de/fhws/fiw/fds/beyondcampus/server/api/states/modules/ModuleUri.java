package de.fhws.fiw.fds.beyondcampus.server.api.states.modules;

import de.fhws.fiw.fds.beyondcampus.Start;

public interface ModuleUri {

    String PATH_ELEMENT = "modules";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
