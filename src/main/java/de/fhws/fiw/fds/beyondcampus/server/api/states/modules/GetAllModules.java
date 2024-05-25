package de.fhws.fiw.fds.beyondcampus.server.api.states.modules;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import jakarta.ws.rs.core.Response;

public class GetAllModules extends AbstractGetCollectionState<Response, Module> {

    public GetAllModules(ServiceContext serviceContext, AbstractQuery<Response,Module> query){
        super(serviceContext,query);
        this.suttonResponse=new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks(){

        addLink(ModuleUri.REL_PATH,
                ModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader());
    }

    public static class AllModules<R> extends AbstractQuery<R,Module>{
        @Override
        protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException{
            return DaoFactory.getInstance().getModuleDao().readAll();
        }
    }
}
