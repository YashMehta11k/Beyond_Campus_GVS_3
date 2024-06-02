package de.fhws.fiw.fds.beyondcampus.server.api.queries;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryPartnerUniSearch<R> extends AbstractQuery<R, PartnerUni> {

    private final String search;

    public QueryPartnerUniSearch(String search,int offset,int size){
        this.search=search;
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    @Override
    protected CollectionModelResult<PartnerUni> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException{
        return DaoFactory.getInstance().getPartnerUniDao().searchPartnerUni(this.search,searchParameter);
    }
}
