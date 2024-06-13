package de.fhws.fiw.fds.beyondcampus.server.database.inmemory;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.ModuleDao;
import de.fhws.fiw.fds.beyondcampus.server.database.PartnerUniModuleDao;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class PartnerUniModuleStorage extends AbstractInMemoryRelationStorage<Module> implements PartnerUniModuleDao {

    final private ModuleDao moduleDao;
    public PartnerUniModuleStorage(ModuleDao moduleDao){
        this.moduleDao = moduleDao;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage(){
        return this.moduleDao;
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
