package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import jakarta.ws.rs.core.Response;

public class DeleteSinglePartnerUni extends AbstractDeleteState<Response, PartnerUni> {

    public DeleteSinglePartnerUni(ServiceContext serviceContext,long modelIdToDelete){
        super(serviceContext,modelIdToDelete);
        this.suttonResponse= new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUni> loadModel(){
        return DaoFactory.getInstance().getPartnerUniDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel(){
        return DaoFactory.getInstance().getPartnerUniDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.GET_ALL_PARTNERUNIS,
                getAcceptRequestHeader());
    }
}
