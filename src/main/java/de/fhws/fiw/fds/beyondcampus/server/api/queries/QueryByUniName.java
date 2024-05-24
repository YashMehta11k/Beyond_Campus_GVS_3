package de.fhws.fiw.fds.beyondcampus.server.api.queries;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByUniName<R> extends AbstractQuery<R, PartnerUni> {

    private String uniName;

    public QueryByUniName(String uniName,int offset,int size){
        this.uniName=uniName;
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    public String getUniName() {
        return this.uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    @Override
    protected CollectionModelResult<PartnerUni> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getPartnerUniDao().readByUniName(this.uniName,
                                                                        searchParameter);
    }
}
