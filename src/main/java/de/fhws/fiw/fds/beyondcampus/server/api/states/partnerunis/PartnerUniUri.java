package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.Start;

public interface PartnerUniUri {

    String PATH_ELEMENT="partnerunis";
    String REL_PATH= Start.CONTEXT_PATH+"/api/"+PATH_ELEMENT;
    String REL_PATH_ID=REL_PATH+"/{id}";
    String URI_TEMPLATE=REL_PATH+"/?uniName={NAME}&uniCountry={COUNTRY}&search={SEARCH}&order={ORDER}";
    String FILTER=REL_PATH+"/uniName={NAME}&uniCountry={COUNTRY}";
    String SEARCH=REL_PATH+"/search={SEARCH}";
}
