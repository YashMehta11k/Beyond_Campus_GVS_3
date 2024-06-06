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
    private boolean isSortDesc;
    private String order;

    public QueryByUniNameAndCountry(String uniName,String uniCountry,String order ,int offset, int size){
        this.uniName=uniName;
        this.uniCountry=uniCountry;
        this.order=order;
        this.isSortDesc=order.startsWith("-");
        this.pagingBehavior=new PagingBehaviorUsingOffsetSize<>(offset,size);
    }

    public String getUniName() {
        return this.uniName;
    }

    public String getUniCountry() {
        return uniCountry;
    }

    public boolean isSortDesc() {
        return isSortDesc;
    }

    public String getOrderAttribute(){
        return this.order;
    }

    public void setUniCountry(String uniCountry) {
        this.uniCountry = uniCountry;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getOrder() {
        return order;
    }

    @Override
    protected CollectionModelResult<PartnerUni> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        searchParameter.setOrderByAttribute((isSortDesc ? "-" : "+") + this.order);
        return DaoFactory.getInstance().getPartnerUniDao().readByUniNameAndCountry(this.uniName, this.uniCountry, searchParameter);
    }

}
