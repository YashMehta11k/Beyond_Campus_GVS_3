package de.fhws.fiw.fds.beyondcampus.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface PartnerUniDao extends IDatabaseAccessObject<PartnerUni> {

    CollectionModelResult<PartnerUni> readByUniNameAndCountry(String uniName,String uniCountry,
                                                    SearchParameter searchParameter);

    CollectionModelResult<PartnerUni>searchPartnerUni(String search,SearchParameter searchParameter);

    void resetDatabase();
}
