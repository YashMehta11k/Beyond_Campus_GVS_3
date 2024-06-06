package de.fhws.fiw.fds.beyondcampus.server.api.queries;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByModOfferedInSem<R> extends AbstractRelationQuery<R, Module> {

    private String offeredInSem;

    public QueryByModOfferedInSem(long primaryId, String offeredInSem, int offset, int size){
        super(primaryId);
        this.offeredInSem=offeredInSem;
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    public String getOfferedInSem() {
        return offeredInSem;
    }

    public void setOfferedInSem(String offeredInSem) {
        this.offeredInSem = offeredInSem;
    }


    @Override
    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException{
        return DaoFactory.getInstance().getPartnerUniModuleDao().readByOfferedInSem(this.primaryId,this.offeredInSem,searchParameter);
    }
}
