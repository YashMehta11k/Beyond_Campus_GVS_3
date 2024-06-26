package de.fhws.fiw.fds.beyondcampus.server.database;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface PartnerUniModuleDao extends IDatabaseRelationAccessObject<Module> {

    CollectionModelResult<Module> readByOfferedInSem(long primaryId, String offeredInSem,
                                                SearchParameter searchParameter);

    void initializeDatabase();

    void resetDatabase();
}
