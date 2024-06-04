package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.api.queries.QueryByUniNameAndCountry;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import jakarta.ws.rs.core.Response;

public class GetAllPartnerUnis extends AbstractGetCollectionState<Response, PartnerUni> {

    public GetAllPartnerUnis(ServiceContext serviceContext, AbstractQuery<Response,PartnerUni> query){
        super(serviceContext,query);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks(){
        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.CREATE_PARTNERUNI,
                getAcceptRequestHeader());

        addLink(PartnerUniUri.REL_PATH+"?uniname={UNINAME}&unicountry={COUNTRY}",
                PartnerUniRelTypes.FILTER_BY_NAME_AND_COUNTRY,
                getAcceptRequestHeader());

        if(this.query instanceof QueryByUniNameAndCountry){
            var query=(QueryByUniNameAndCountry<Response>) this.query;
            if(!query.getUniName().isEmpty() || !query.getUniCountry().isEmpty()){

                addLink(PartnerUniUri.REL_PATH+"?uniname={id}&unicountry={id}&order={id}",
                        PartnerUniRelTypes.FILTER_BY_NAME_ORDER_BY_UNINAME,
                        getAcceptRequestHeader(),
                        query.getUniName(),
                        query.getUniCountry(),
                        query.getOrderAttributeForUniName());

                addLink(PartnerUniUri.REL_PATH+"?uniname={id}&unicountry={id}&order={id}",
                        PartnerUniRelTypes.FILTER_BY_NAME_ORDER_BY_SEMSTART,
                        getAcceptRequestHeader(),
                        query.getUniName(),
                        query.getUniCountry(),
                        query.getOrderAttributeForUniSemStart());
            }
        }
    }
}
