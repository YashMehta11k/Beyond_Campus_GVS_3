package de.fhws.fiw.fds.beyondcampus.server.api.states.dispatcher;

import de.fhws.fiw.fds.beyondcampus.server.api.states.modules.ModuleRelTypes;
import de.fhws.fiw.fds.beyondcampus.server.api.states.modules.ModuleUri;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis.PartnerUniRelTypes;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis.PartnerUniUri;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import jakarta.ws.rs.core.Response;

public class GetDispatcher extends AbstractGetDispatcherState<Response> {

    public GetDispatcher(ServiceContext serviceContext){
        super(serviceContext);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.GET_ALL_PARTNERUNIS,
                getAcceptRequestHeader());

        addLink(PartnerUniUri.REL_PATH,
                PartnerUniRelTypes.CREATE_PARTNERUNI,
                getAcceptRequestHeader());

        addLink(ModuleUri.REL_PATH,
                ModuleRelTypes.GET_ALL_MODULES,
                getAcceptRequestHeader());
    }
}
