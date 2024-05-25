package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis;

import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.ws.rs.core.Response;

public class PostNewPartnerUni extends AbstractPostState<Response, PartnerUni> {

    public PostNewPartnerUni(ServiceContext serviceContext,PartnerUni modelToStore){
        super(serviceContext,modelToStore);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel(){
        return DaoFactory.getInstance().getPartnerUniDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks(){
        addLink(PartnerUniUri.REL_PATH_ID,
                PartnerUniRelTypes.GET_SINGLE_PARTNERUNI,
                getAcceptRequestHeader(),
                this.modelToStore.getId());
    }
}
