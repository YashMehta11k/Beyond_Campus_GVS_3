package de.fhws.fiw.fds.beyondcampus.server.api.queries;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByUniNameAndCountry<R> extends AbstractQuery<R, PartnerUni> {

    private String uniName;
    private String uniCountry;

    public QueryByUniNameAndCountry(String uniName,String uniCountry, int offset, int size){
        this.uniName=uniName;
        this.uniCountry=uniCountry;
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    public String getUniName() {
        return this.uniName;
    }

    public String getUniCountry() {
        return uniCountry;
    }

    public void setUniCountry(String uniCountry) {
        this.uniCountry = uniCountry;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    @Override
    protected CollectionModelResult<PartnerUni> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getPartnerUniDao().readByUniNameAndCountry(this.uniName, this.uniCountry,searchParameter);
    }
}
