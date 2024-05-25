package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.ws.rs.core.Response;

public class PutSinglePartnerUni extends AbstractPutState<Response, PartnerUni> {

    public PutSinglePartnerUni(ServiceContext serviceContext,long requestedId,PartnerUni modelToUpdate){
        super(serviceContext,requestedId,modelToUpdate);
        this.suttonResponse= new JerseyResponse<>();
    }

    @Override
    protected SingleModelResult<PartnerUni> loadModel(){
        return DaoFactory.getInstance().getPartnerUniDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel(){
        return DaoFactory.getInstance().getPartnerUniDao().update(this.modelToUpdate);
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(AbstractModel modelFromDatabase){
        return this.suttonRequest.clientKnowsCurrentModel(modelFromDatabase);
    }

    @Override
    protected void defineHttpCaching(){
        this.suttonResponse.cacheControl(CachingUtils.create30SecondsPublicCaching());
    }

    @Override
    protected void defineTransitionLinks(){
        addLink(PartnerUniUri.REL_PATH_ID,
                PartnerUniRelTypes.GET_SINGLE_PARTNERUNI,
                getAcceptRequestHeader(),
                this.modelToUpdate.getId());
    }
}
