package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules;

import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import jakarta.ws.rs.core.Response;

public class PostNewModuleOfPartnerUni extends AbstractPostRelationState<Response,Module> {

    public PostNewModuleOfPartnerUni(ServiceContext serviceContext,long primaryId, Module modelToStore){
        super(serviceContext,primaryId,modelToStore);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override protected NoContentResult saveModel(){
        return DaoFactory.getInstance().getPartnerUniModuleDao().create(this.primaryId,this.modelToStore);
    }

    @Override protected void defineTransitionLinks(){

        addLink(PartnerUniModuleUri.REL_PATH_ID,
                PartnerUniModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId, this.modelToStore.getId());

        addLink(PartnerUniModuleUri.REL_PATH,
                PartnerUniModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

    }
}
