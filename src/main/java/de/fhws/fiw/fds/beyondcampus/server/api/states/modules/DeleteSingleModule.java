package de.fhws.fiw.fds.beyondcampus.server.api.states.modules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis.PartnerUniRelTypes;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis.PartnerUniUri;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import jakarta.ws.rs.core.Response;

public class DeleteSingleModule extends AbstractDeleteState<Response, Module> {

    public DeleteSingleModule(ServiceContext serviceContext,long modelIdToDelete){
        super(serviceContext,modelIdToDelete);
        this.suttonResponse= new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<Module> loadModel(){
        return DaoFactory.getInstance().getModuleDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel(){
        DaoFactory.getInstance().getPartnerUniModuleDao().deleteRelationsToSecondary(this.modelIdToDelete);
        return DaoFactory.getInstance().getPartnerUniDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.GET_ALL_PARTNERUNIS,
                getAcceptRequestHeader());
    }
}
