package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.ws.rs.core.Response;

public class GetSingleModuleofPartnerUni extends AbstractGetRelationState<Response, Module> {

    public GetSingleModuleofPartnerUni(ServiceContext serviceContext,long primaryId,long requestedId){
        super(serviceContext,primaryId,requestedId);
        this.suttonResponse= new JerseyResponse<>();
    }

    @Override
    protected boolean clientKnowsCurrentModelState(AbstractModel modelFromDatabase){
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        final String modelFromDBEtag = EtagGenerator.createEtag(this.requestedModel.getResult());
        this.suttonResponse.entityTag(modelFromDBEtag);
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected SingleModelResult<Module> loadModel(){
        SingleModelResult<Module> module= DaoFactory.getInstance().getModuleDao().readById(this.requestedId);
        if (isPartnerUniLinkedToThisModule()) {
            module.getResult().setPrimaryId(this.primaryId);
        }
        return module;
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(PartnerUniModuleUri.REL_PATH_SHOW_ONLY_LINKED,
                PartnerUniModuleRelTypes.GET_ALL_LINKED_MODULES,
                getAcceptRequestHeader(),
                this.primaryId
                );

        addLink(PartnerUniModuleUri.REL_PATH_ID,
                PartnerUniModuleRelTypes.UPDATE_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId,this.requestedId);

        addLink(PartnerUniModuleUri.REL_PATH_ID,
                PartnerUniModuleRelTypes.DELETE_MODULE_FROM_PARTNERUNI,
                getAcceptRequestHeader(),
                this.primaryId,this.requestedId);
    }

    private boolean isPartnerUniLinkedToThisModule(){
        return !DaoFactory.getInstance()
                .getPartnerUniModuleDao()
                .readById(this.primaryId,this.requestedId)
                .isEmpty();
    }
}
