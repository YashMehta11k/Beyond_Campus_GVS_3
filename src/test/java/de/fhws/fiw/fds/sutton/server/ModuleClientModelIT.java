package de.fhws.fiw.fds.sutton.server;

import de.fhws.fiw.fds.beyondcampus.client.web.PartnerUniModuleWebClient;
import de.fhws.fiw.fds.beyondcampus.client.web.PartnerUniModuleWebResponse;
import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModuleClientModelIT {

    private static final String BASE_URL = "http://localhost:8080/beyondcampus/api";
    private static final String PARTNER_UNI_URL = BASE_URL + "/partnerunis/1";
    private static final String MODULE_URL = PARTNER_UNI_URL + "/modules";

    private PartnerUniModuleWebClient client;

    @BeforeEach
    public void setUp() {
        client = new PartnerUniModuleWebClient();
    }

    @Test
    public void testCreateModule() throws IOException {
        ModuleClientModel module = new ModuleClientModel("Module1", "Prof. Smith", 5, ModuleClientModel.OfferedInSem.Autumn);
        PartnerUniModuleWebResponse response = client.postNewModuleOfPartnerUni(MODULE_URL, module);

        assertEquals(201, response.getLastStatusCode());
    }

    @Test
    public void testGetAllModules() throws IOException {
        PartnerUniModuleWebResponse response = client.getModuleCollectionOfPartnerUnis(MODULE_URL);

        assertEquals(200, response.getLastStatusCode());
        Collection<ModuleClientModel> modules = response.getResponseData();
        assertNotNull(modules);
    }

    @Test
    public void testGetSingleModule() throws IOException {

        ModuleClientModel module = new ModuleClientModel("Module1", "Prof. Smith", 5, ModuleClientModel.OfferedInSem.Autumn);
        PartnerUniModuleWebResponse createResponse = client.postNewModuleOfPartnerUni(MODULE_URL, module);

        String singleModuleUrl = MODULE_URL + "/1";
        PartnerUniModuleWebResponse getResponse = client.getSingleModuleOfPartnerUni(singleModuleUrl);

        assertEquals(200, getResponse.getLastStatusCode());
        ModuleClientModel retrievedModule = getResponse.getResponseData().iterator().next();
        assertEquals("Module1", retrievedModule.getModName());
    }

    @Test
    public void testUpdateModule() throws IOException {

        ModuleClientModel module = new ModuleClientModel("Module1", "Prof. Smith", 5, ModuleClientModel.OfferedInSem.Autumn);
        PartnerUniModuleWebResponse createResponse = client.postNewModuleOfPartnerUni(MODULE_URL, module);

        String singleModuleUrl = MODULE_URL + "/1";
        module.setModName("UpdatedModule");

        PartnerUniModuleWebResponse updateResponse = client.putModuleOfPartnerUni(singleModuleUrl, module);
        assertEquals(204, updateResponse.getLastStatusCode());

        PartnerUniModuleWebResponse getResponse = client.getSingleModuleOfPartnerUni(singleModuleUrl);
        ModuleClientModel updatedModule = getResponse.getResponseData().iterator().next();
        assertEquals("UpdatedModule", updatedModule.getModName());
    }

    @Test
    public void testDeleteModule() throws IOException {

        ModuleClientModel module = new ModuleClientModel("Module1", "Prof. Smith", 5, ModuleClientModel.OfferedInSem.Autumn);
        PartnerUniModuleWebResponse createResponse = client.postNewModuleOfPartnerUni(MODULE_URL, module);

        String singleModuleUrl = MODULE_URL + "/1";

        PartnerUniModuleWebResponse deleteResponse = client.deleteModuleOfPartnerUni(singleModuleUrl);
        assertEquals(204, deleteResponse.getLastStatusCode());

        PartnerUniModuleWebResponse getResponse = client.getSingleModuleOfPartnerUni(singleModuleUrl);
        assertEquals(404, getResponse.getLastStatusCode());
    }
}
