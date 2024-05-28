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
    final private ModuleDao moduleStorage;

    public PartnerUniModuleStorage(ModuleDao moduleStorage){
        this.moduleStorage=moduleStorage;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage(){
        return this.moduleStorage;
    }

    @Override
    public CollectionModelResult<Module> readByModName(long primaryId, String modName,boolean showAll,
                                                       SearchParameter searchParameter){
        if(showAll){
            return InMemoryPaging.page(
                    this.readAllByPredicate(primaryId,(p) -> modName.isEmpty() || p.getModName().equals(modName)),
                    searchParameter.getOffset(),searchParameter.getSize()
            );
        }else{
            return InMemoryPaging.page(
                    this.readAllLinkedByPredicate(primaryId,(p) -> modName.isEmpty() || p.getModName().equals(modName)),
                    searchParameter.getOffset(),searchParameter.getSize()
            );
        }
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
