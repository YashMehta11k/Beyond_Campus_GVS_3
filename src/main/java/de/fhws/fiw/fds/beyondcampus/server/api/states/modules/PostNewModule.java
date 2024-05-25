package de.fhws.fiw.fds.beyondcampus.server.api.states.modules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.ws.rs.core.Response;

public class PostNewModule extends AbstractPostState<Response, Module> {

    public PostNewModule(ServiceContext serviceContext, Module moduleToStore){
        super(serviceContext,moduleToStore);
        this.suttonResponse= new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel(){
        return DaoFactory.getInstance().getModuleDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(ModuleUri.REL_PATH_ID,
                ModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.modelToStore.getId());
    }
}
