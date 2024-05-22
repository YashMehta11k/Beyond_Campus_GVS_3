package de.fhws.fiw.fds.beyondcampus.server.database.inmemory;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.PartnerUniDao;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import java.util.function.Predicate;

public class PartnerUniStorage extends AbstractInMemoryStorage<PartnerUni> implements PartnerUniDao {

    @Override
    public CollectionModelResult<PartnerUni> readByUniName(String uniName,
                                                           SearchParameter searchParameter){
        return InMemoryPaging.page(this.readAllByPredicate(
                byUniName(uniName),
                searchParameter
        ),searchParameter.getOffset(),searchParameter.getSize());
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<PartnerUni> byUniName(String uniName){
        return p -> (uniName.isEmpty() || p.getUniName().equals(uniName));
    }
}
