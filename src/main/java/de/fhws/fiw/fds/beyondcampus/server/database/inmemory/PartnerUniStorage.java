package de.fhws.fiw.fds.beyondcampus.server.database.inmemory;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.PartnerUniDao;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartnerUniStorage extends AbstractInMemoryStorage<PartnerUni> implements PartnerUniDao {

    public PartnerUniStorage(){
        super();
        populateData(50);
    }

    @Override
    public CollectionModelResult<PartnerUni> readByUniNameAndCountry(String uniName, String uniCountry, SearchParameter searchParameter) {
        List<PartnerUni> filteredAndSortedResult = filterAndSortByUniNameAndCountry(uniName, uniCountry, searchParameter);

        CollectionModelResult<PartnerUni> sortedCollectionResult = new CollectionModelResult<>(filteredAndSortedResult);
        return InMemoryPaging.page(sortedCollectionResult, searchParameter.getOffset(), searchParameter.getSize());
    }

    @Override
    public CollectionModelResult<PartnerUni> searchPartnerUni(String search , SearchParameter searchParameter){
        List<PartnerUni> filteredAndSortedResult = filterAndSortBySearch(search, searchParameter);

        CollectionModelResult<PartnerUni> sortedCollectionResult = new CollectionModelResult<>(filteredAndSortedResult);
        return InMemoryPaging.page(sortedCollectionResult, searchParameter.getOffset(), searchParameter.getSize());
    }

    private List<PartnerUni> filterAndSortByUniNameAndCountry(String uniName, String uniCountry, SearchParameter searchParameter) {
        return this.storage.values()
                .stream()
                .filter(byUniNameAndCountry(uniName, uniCountry))
                .sorted(getComparator(searchParameter.getOrderByAttribute()))
                .collect(Collectors.toList());
    }

    private List<PartnerUni> filterAndSortBySearch(String search, SearchParameter searchParameter) {
        return this.storage.values()
                .stream()
                .filter(bySearch(search))
                .sorted(getComparator(searchParameter.getOrderByAttribute()))
                .collect(Collectors.toList());
    }

    private Predicate<PartnerUni> byUniNameAndCountry(String uniName,String uniCountry){
        return p -> (uniName.isEmpty() || p.getUniName().equals(uniName)) && (uniCountry.isEmpty() || p.getUniCountry().equals(uniCountry));
    }

    private Predicate<PartnerUni> bySearch(String search){
        return p -> search.isEmpty() || p.getUniName().contains(search) || p.getUniCountry().contains(search) || p.getUniContactPerson().contains(search)|| p.getDepartmentName().contains(search) || p.getDepartmentWebsite().contains(search);
    }

    private Comparator<PartnerUni> getComparator(String orderAttribute) {
        boolean isDesc = orderAttribute.startsWith("-");
        String attribute = orderAttribute.substring(1);

        Comparator<PartnerUni> comparator;

        switch (attribute) {
            case "uniname":
                comparator = Comparator.comparing(PartnerUni::getUniName);
                break;
            case "semStart":
                comparator = Comparator.comparing(PartnerUni::getUpcomingAutumnSem);
                break;
            default:
                comparator = Comparator.comparing(PartnerUni::getId);
        }

        return isDesc ? comparator.reversed() : comparator;
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
}
