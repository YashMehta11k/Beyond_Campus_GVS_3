package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.api.queries.QueryByUniNameAndCountry;
import de.fhws.fiw.fds.beyondcampus.server.api.queries.QueryPartnerUniSearch;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import jakarta.ws.rs.core.Response;

public class GetAllPartnerUnis extends AbstractGetCollectionState<Response, PartnerUni> {

    public GetAllPartnerUnis(ServiceContext serviceContext, AbstractQuery<Response, PartnerUni> query) {
        super(serviceContext, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {

        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.CREATE_PARTNERUNI,
                getAcceptRequestHeader());

        addLink(PartnerUniUri.FILTER,
                PartnerUniRelTypes.FILTER_PARTNERUNIS,
                getAcceptRequestHeader());

        addLink(PartnerUniUri.SEARCH,
                PartnerUniRelTypes.SEARCH_PARTNERUNIS,
                getAcceptRequestHeader());

        if(this.query instanceof QueryByUniNameAndCountry){
            var query=(QueryByUniNameAndCountry<Response>)this.query;
            String reverse;

            addLink(PartnerUniUri.REL_PATH+"?order={uniname,-uniname,semstart,-semstart}",
                    PartnerUniRelTypes.ORDER_PARTNERUNIS,
                    getAcceptRequestHeader());

            if(query.getOrderAttribute().charAt(0)=='+'){
                reverse='-'+query.getOrderAttribute();
            }else{
                reverse='+'+query.getOrderAttribute();
            }
            addLink(PartnerUniUri.REL_PATH+"/order={"+reverse+"}",
                    PartnerUniRelTypes.REVERSE_ORDER,
                    getAcceptRequestHeader());

        }

        addLink(PartnerUniUri.URI_TEMPLATE,
                PartnerUniRelTypes.URI_TEMPLATE,
                getAcceptRequestHeader());

    }
}
