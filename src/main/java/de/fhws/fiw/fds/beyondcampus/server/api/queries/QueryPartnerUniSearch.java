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
    private boolean isSortDesc;
    private String order;

    public QueryPartnerUniSearch(String search,String order,int offset,int size){
        this.search=search;
        this.order=order;
        this.isSortDesc=order.startsWith("-");
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    public boolean isSortDesc() {
        return isSortDesc;
    }

    public String getOrderAttribute(){
        return this.order;
    }

    public String getOrder() {
        return order;
    }

    @Override
    protected CollectionModelResult<PartnerUni> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException{
        searchParameter.setOrderByAttribute((isSortDesc ? "-" : "+") + this.order);
        return DaoFactory.getInstance().getPartnerUniDao().searchPartnerUni(this.search,searchParameter);
    }
}
