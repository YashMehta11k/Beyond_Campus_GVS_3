package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import jakarta.ws.rs.core.Response;

public class DeleteSingleModuleOfPartnerUni extends AbstractDeleteRelationState<Response, Module> {

    public DeleteSingleModuleOfPartnerUni(ServiceContext serviceContext,long modelIdToDelete,long primaryId){
        super(serviceContext,modelIdToDelete,primaryId);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<Module> loadModel(){
        return DaoFactory.getInstance().getPartnerUniModuleDao().readById(this.primaryId,this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel(){
        return DaoFactory.getInstance().getPartnerUniModuleDao().deleteRelation(this.primaryId,this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(PartnerUniModuleUri.REL_PATH,
                PartnerUniModuleRelTypes.GET_ALL_LINKED_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);
    }
}
