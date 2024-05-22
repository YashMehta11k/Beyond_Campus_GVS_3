package de.fhws.fiw.fds.beyondcampus.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface PartnerUniDao extends IDatabaseAccessObject<PartnerUni> {

    CollectionModelResult<PartnerUni> readByUniName(String uniName,
                                                    SearchParameter searchParameter);

    void resetDatabase();
}
