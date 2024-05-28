package de.fhws.fiw.fds.beyondcampus.server.api.queries;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryByModName<R> extends AbstractRelationQuery<R, Module> {

    private String modName;
    private boolean showAll;

    private int waitingTime;

    public QueryByModName(long primaryId,String modName,boolean showAll,int offset,int size){
        super(primaryId);
        this.modName=modName;
        this.showAll=showAll;
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    @Override
    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException{
        return DaoFactory.getInstance().getPartnerUniModuleDao().readByModName(this.primaryId,this.modName,this.showAll,searchParameter);
    }
}
