package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import jakarta.ws.rs.core.Response;

public class GetAllModulesofPartnerUni extends AbstractGetCollectionRelationState<Response, Module> {

    public GetAllModulesofPartnerUni(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response,Module> query){
        super(serviceContext,primaryId,query);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(PartnerUniModuleUri.REL_PATH,
                PartnerUniModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(PartnerUniModuleUri.FILTER_BY_SEM,
                PartnerUniModuleRelTypes.FILTER_BY_SEMESTER,
                getAcceptRequestHeader(),
                this.primaryId);
    }
}
