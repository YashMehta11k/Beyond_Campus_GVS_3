package de.fhws.fiw.fds.sutton.server;


import com.github.javafaker.Faker;
import de.fhws.fiw.fds.beyondcampus.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.beyondcampus.client.rest.BeyondCampusRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestDemoAppIT {
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

        client.createPartnerUni(partnerUni);
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_partnerUni_and_get_new_partnerUni() throws IOException
    {
        client.start();

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

        assertTrue(client.isUpdatePartnerUniAllowed());

        partnerUni.setUniName("UpdatedUni");
        partnerUni.setUniCountry("UpdatedCountry");

        client.updatePartnerUni(partnerUni);
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

        client.start();
        client.getAllPartnerUnis();
        assertEquals(200, client.getLastStatusCode());
    }
}

