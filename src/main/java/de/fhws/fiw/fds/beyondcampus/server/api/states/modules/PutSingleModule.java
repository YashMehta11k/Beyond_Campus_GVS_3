package de.fhws.fiw.fds.beyondcampus.server.api.states.modules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.ws.rs.core.Response;

public class PutSingleModule extends AbstractPutState<Response, Module> {

    public PutSingleModule(ServiceContext serviceContext,long requestedId,Module modelToUpdate){
        super(serviceContext,requestedId,modelToUpdate);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase) {
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching() {
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected SingleModelResult<Module> loadModel(){
        return DaoFactory.getInstance().getModuleDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel(){
        return DaoFactory.getInstance().getModuleDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(ModuleUri.REL_PATH_ID,
                ModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }
}
