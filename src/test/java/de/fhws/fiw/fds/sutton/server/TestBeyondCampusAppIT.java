package de.fhws.fiw.fds.sutton.server;


import com.github.javafaker.Faker;
import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.beyondcampus.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.beyondcampus.client.rest.BeyondCampusRestClient;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestBeyondCampusAppIT {
    final private Faker faker = new Faker();
    private BeyondCampusRestClient client;

    @BeforeEach
    public void setUp() throws IOException{
       this.client = new BeyondCampusRestClient();
       this.client.resetDatabase();
    }

    @Test
    public void test_dispatcher_is_available() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    public void test_dispatcher_is_get_all_partnerUni_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllPartnerUnisAllowed());
    }

    @Test
    public void test_create_partnerUni_is_create_partneruni_allowed() throws IOException {
        client.start();
        assertTrue(client.isCreatePartnerUniAllowed());
    }

    @Test
    void test_create_partnerUni() throws IOException
    {
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_partnerUni_and_get_new_partnerUni() throws IOException
    {
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        var partnerUniFromServer = client.partnerUniData().getFirst();
        assertEquals( "UniExample", partnerUniFromServer.getUniName() );
    }

    @Test
    void test_create_5_partnerUni_and_get_all() throws IOException
    {
        for( int i=0; i<5; i++ ) {
            client.start();

            var partnerUni = new PartnerUniClientModel();
            partnerUni.setUniName(faker.name().name());
            partnerUni.setUniCountry(faker.country().name());
            partnerUni.setDepartmentName(faker.name().name());
            partnerUni.setUniContactPerson(faker.name().name());
            partnerUni.setDepartmentName(faker.name().name());
            partnerUni.setDepartmentWebsite(faker.internet().emailAddress());
            partnerUni.setMaxIncomingStudents(5);
            partnerUni.setMaxOutgoingStudents(5);
            partnerUni.setUpcomingAutumnSem(LocalDate.of(2024,9,1));
            partnerUni.setUpcomingSpringSem(LocalDate.of(2024,3,1));

            client.createPartnerUni(partnerUni);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue( client.isGetAllPartnerUnisAllowed() );

        client.getAllPartnerUnis();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.partnerUniData().size());

        client.setPartnerUniCursor(0);
        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    void test_create_partnerUni_and_update_partnerUni() throws IOException {
        client.start();

        var partnerUni = new PartnerUniClientModel();
        partnerUni.setUniName("OriginalUni");
        partnerUni.setUniCountry("OriginalCountry");
        partnerUni.setDepartmentName("OriginalDepartment");
        partnerUni.setUniContactPerson("OriginalContact");
        partnerUni.setDepartmentName("OriginalDepartment");
        partnerUni.setDepartmentWebsite("www.Original.com");
        partnerUni.setMaxIncomingStudents(5);
        partnerUni.setMaxOutgoingStudents(5);
        partnerUni.setUpcomingAutumnSem(LocalDate.of(2024, 9, 1));
        partnerUni.setUpcomingSpringSem(LocalDate.of(2024, 3, 1));

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSinglePartnerUniAllowed());
        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        var existingPartnerUni=client.partnerUniData().getFirst();

        assertTrue(client.isUpdatePartnerUniAllowed());

        existingPartnerUni.setUniName("UpdatedUni");
        existingPartnerUni.setUniCountry("UpdatedCountry");

        client.updatePartnerUni(existingPartnerUni);
        assertEquals(204, client.getLastStatusCode());

        var updatedPartnerUni = client.partnerUniData().getFirst();
        assertEquals("UpdatedUni", updatedPartnerUni.getUniName());
        assertEquals("UpdatedCountry", updatedPartnerUni.getUniCountry());
    }

    @Test
    void test_create_PartnerUni_and_delete_partnerUni() throws IOException {
        client.start();

        var partnerUni = new PartnerUniClientModel();
        partnerUni.setUniName("ToDeleteUni");
        partnerUni.setUniCountry("ToDeleteCountry");
        partnerUni.setDepartmentName("ToDeleteDepartment");
        partnerUni.setUniContactPerson("ToDeleteContact");
        partnerUni.setMaxIncomingStudents(5);
        partnerUni.setMaxOutgoingStudents(5);
        partnerUni.setUpcomingAutumnSem(LocalDate.of(2024, 9, 1));
        partnerUni.setUpcomingSpringSem(LocalDate.of(2024, 3, 1));

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSinglePartnerUniAllowed());
        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isDeletePartnerUniAllowed());

        client.deletePartnerUni();
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetAllPartnerUnisAllowed());
        client.getAllPartnerUnis();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    void test_is_get_all_modules_link_available() throws IOException{
        client.start();

        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
    }

    @Test
    void test_is_create_module_of_PartnerUni_link_available() throws IOException{
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());
    }

    @Test
    void test_create_module_of_partnerUni() throws IOException{
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());

        var module=getModuleClientModel();

        client.createModule(module);
        assertEquals(201,client.getLastStatusCode());
    }

    @Test
    void test_create_module_of_partnerUni_and_get_new_single_module() throws IOException{
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());

        var module=getModuleClientModel();

        client.createModule(module);
        assertEquals(201,client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertEquals(200,client.getLastStatusCode());

        var moduleFromServer=client.moduleData().getFirst();
        assertEquals("ModExample",moduleFromServer.getModName());
    }

    @Test
    void test_create_5_modules_of_partnerUni_and_get_all_modules() throws IOException{

        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSinglePartnerUniAllowed());

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        for(int i=0;i<5;i++) {

            assertTrue(client.isCreateModuleAllowed());

            var module = getModuleClientModel();

            client.createModule(module);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue( client.isGetAllPartnerUnisAllowed() );
        client.getAllPartnerUnis();
        assertEquals(200, client.getLastStatusCode());
        client.setPartnerUniCursor(0);
        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200,client.getLastStatusCode());
        assertEquals(5,client.moduleData().size());
    }

    @Test
    void test_create_module_of_partnerUni_and_update_existing_module() throws IOException{
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());

        var module=getModuleClientModel();

        client.createModule(module);
        assertEquals(201,client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertEquals(200,client.getLastStatusCode());

        var existingModuleFromServer=client.moduleData().getLast();

        assertTrue(client.isUpdateModuleAllowed());
        existingModuleFromServer.setNoOfCredits(10);
        existingModuleFromServer.setOfferedInSem(ModuleClientModel.OfferedInSem.Spring);

        client.updateModule(existingModuleFromServer);
        assertEquals(204,client.getLastStatusCode());

        var updatedModule=client.moduleData().getFirst();
        assertEquals(10,updatedModule.getNoOfCredits());
        assertEquals(ModuleClientModel.OfferedInSem.Spring,updatedModule.getOfferedInSem());
    }

    @Test
    void test_create_module_of_partnerUni_and_delete_module() throws IOException {
        client.start();

        var partnerUni = getPartnerUniClientModel();

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerUniAllowed() );

        client.getSinglePartnerUni();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());

        var module=getModuleClientModel();

        client.createModule(module);
        assertEquals(201,client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertEquals(200,client.getLastStatusCode());

        assertTrue(client.isDeleteModuleAllowed());
        client.deleteModule();
        assertEquals(204,client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
    }

    private static @NotNull PartnerUniClientModel getPartnerUniClientModel() {
        var partnerUni = new PartnerUniClientModel();
        partnerUni.setUniName("UniExample");
        partnerUni.setUniCountry("CountryExample");
        partnerUni.setDepartmentName("DepartmentExample");
        partnerUni.setUniContactPerson("ContactPersonExample");
        partnerUni.setDepartmentName("DepartExample");
        partnerUni.setDepartmentWebsite("www.Example.com");
        partnerUni.setMaxIncomingStudents(5);
        partnerUni.setMaxOutgoingStudents(5);
        partnerUni.setUpcomingAutumnSem(LocalDate.of(2024,9,1));
        partnerUni.setUpcomingSpringSem(LocalDate.of(2024,3,1));
        return partnerUni;
    }

    private static @NotNull ModuleClientModel getModuleClientModel() {
        var module=new ModuleClientModel();
        module.setModName("ModExample");
        module.setNoOfCredits(4);
        module.setOfferedInSem(ModuleClientModel.OfferedInSem.Autumn);
        module.setProfessorName("ProfExample");
        return module;
    }
}

