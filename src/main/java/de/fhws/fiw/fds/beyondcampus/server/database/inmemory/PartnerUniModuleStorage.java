package de.fhws.fiw.fds.beyondcampus.server.database.inmemory;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.DaoFactory;
import de.fhws.fiw.fds.beyondcampus.server.database.ModuleDao;
import de.fhws.fiw.fds.beyondcampus.server.database.PartnerUniModuleDao;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class PartnerUniModuleStorage extends AbstractInMemoryRelationStorage<Module> implements PartnerUniModuleDao {
    public PartnerUniModuleStorage(){
        super();
        for(int index=0;index<50;index++) {
            this.storage.put(1L,index+1L);
        }
        for(int index=50;index<75;index++) {
            this.storage.put(index-49+1L,index+1L);
        }
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage(){
        return DaoFactory.getInstance().getModuleDao();
    }

    @Override
    public CollectionModelResult<Module> readByOfferedInSem(long primaryId, String offeredInSem,SearchParameter searchParameter){

            return InMemoryPaging.page(
                    this.readAllLinkedByPredicate(primaryId,(p) -> offeredInSem.isEmpty() || p.getOfferedInSem().equals(Module.OfferedInSem.valueOf(offeredInSem))),
                    searchParameter.getOffset(),searchParameter.getSize()
            );
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId,SearchParameter searchParameter){
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> true),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public void resetDatabase(){

    }

    @Override
    public void initializeDatabase(){

    }
}
