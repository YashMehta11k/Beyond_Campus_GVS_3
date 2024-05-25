package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
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
    }
}
