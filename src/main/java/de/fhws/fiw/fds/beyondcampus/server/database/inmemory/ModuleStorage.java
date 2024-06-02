package de.fhws.fiw.fds.beyondcampus.server.database.inmemory;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.database.ModuleDao;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

import java.util.stream.IntStream;

public class ModuleStorage extends AbstractInMemoryStorage<Module> implements ModuleDao {

    public ModuleStorage(){
        super();
        populateData(40);
    }

    private void populateData( final int numberOfElements )
    {
        IntStream.range( 0, numberOfElements ).forEach( this::createOneNewModule );
    }

    private void createOneNewModule(final int index){
        Module.OfferedInSem sem;
        if(index%2==0){
            sem= Module.OfferedInSem.Autumn;
        }else{
            sem=Module.OfferedInSem.Spring;
        }
        create(new Module("M"+index,"Dr.P"+index,4+index, sem));
    }
}
