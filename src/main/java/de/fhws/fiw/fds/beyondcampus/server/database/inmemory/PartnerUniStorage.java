package de.fhws.fiw.fds.beyondcampus.server.database.inmemory;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.PartnerUniDao;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PartnerUniStorage extends AbstractInMemoryStorage<PartnerUni> implements PartnerUniDao {

    public PartnerUniStorage(){
        super();
        populateData(50);
    }

    @Override
    public CollectionModelResult<PartnerUni> readByUniNameAndCountry(String uniName,String uniCountry,
                                                           SearchParameter searchParameter){
        return InMemoryPaging.page(this.readAllByPredicate(
                byUniNameAndCountry(uniName,uniCountry),
                searchParameter
        ),searchParameter.getOffset(),searchParameter.getSize());
    }

    @Override
    public CollectionModelResult<PartnerUni> searchPartnerUni(String search , SearchParameter searchParameter){
        return InMemoryPaging.page(this.readAllByPredicate(
                bySearch(search),
                searchParameter
        ),searchParameter.getOffset(),searchParameter.getSize());
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private void populateData( final int numberOfElements )
    {
        IntStream.range( 0, numberOfElements ).forEach( this::createOnePartnerUni );
    }

    private void createOnePartnerUni(final int index){
        create(new PartnerUni("Uni"+index,
                "C"+index,
                "P"+index,
                "D"+index,
                "www.D"+index,
                5+index,
                10+index,
                LocalDate.of(2024+index,3,1),
                LocalDate.of(2024+index,9,1)));
    }

    private Predicate<PartnerUni> byUniNameAndCountry(String uniName,String uniCountry){
        return p -> (uniName.isEmpty() || p.getUniName().equals(uniName)) && (uniCountry.isEmpty() || p.getUniCountry().equals(uniCountry));
    }

    private Predicate<PartnerUni> bySearch(String search){
        return p -> search.isEmpty() || p.getUniName().startsWith(search) || p.getUniCountry().startsWith(search) || p.getUniContactPerson().startsWith(search)|| p.getDepartmentName().startsWith(search) || p.getDepartmentWebsite().startsWith(search);
    }
}
