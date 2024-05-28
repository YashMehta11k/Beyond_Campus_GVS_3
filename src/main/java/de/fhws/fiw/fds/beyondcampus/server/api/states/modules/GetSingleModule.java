package de.fhws.fiw.fds.beyondcampus.server.api.states.modules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
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

public class GetSingleModule extends AbstractGetState<Response, Module> {

    public GetSingleModule(ServiceContext serviceContext,long requestedId){
        super(serviceContext,requestedId);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<Module> loadModel(){
        return DaoFactory.getInstance().getModuleDao().readById(this.requestedId);
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

        addLink(ModuleUri.REL_PATH,
                ModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader());

        addLink(ModuleUri.REL_PATH_ID,
                ModuleRelTypes.UPDATE_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.requestedId);

        addLink(PartnerUniModuleUri.REL_PATH_ID,
                PartnerUniModuleRelTypes.DELETE_MODULE_FROM_PARTNERUNI,
                getAcceptRequestHeader(),
                this.requestedId);
    }
}
