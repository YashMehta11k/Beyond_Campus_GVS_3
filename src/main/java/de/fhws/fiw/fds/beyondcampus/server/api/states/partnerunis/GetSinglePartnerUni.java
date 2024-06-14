package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules.PartnerUniModuleRelTypes;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules.PartnerUniModuleUri;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.ws.rs.core.Response;

public class GetSinglePartnerUni extends AbstractGetState<Response, PartnerUni> {

    public GetSinglePartnerUni(ServiceContext serviceContext,long requestedId){
        super(serviceContext,requestedId);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUni> loadModel(){
        return DaoFactory.getInstance().getPartnerUniDao().readById(this.requestedId);
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase){
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching(){
        final String eTagOfModel= EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(eTagOfModel);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected void defineTransitionLinks(){
        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.GET_ALL_PARTNERUNIS,
                getAcceptRequestHeader());

        addLink(PartnerUniUri.REL_PATH_ID,
                PartnerUniRelTypes.UPDATE_SINGLE_PARTNERUNI,
                getAcceptRequestHeader(),
                this.requestedId);

        addLink(PartnerUniUri.REL_PATH_ID,
                PartnerUniRelTypes.DELETE_SINGLE_PARTNERUNI,
                getAcceptRequestHeader(),
                this.requestedId);

        addLink(PartnerUniModuleUri.REL_PATH,
                PartnerUniModuleRelTypes.GET_ALL_LINKED_MODULES,
                getAcceptRequestHeader(),
                this.requestedId);

        addLink(PartnerUniModuleUri.REL_PATH,
                PartnerUniModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.requestedId);
    }
}
