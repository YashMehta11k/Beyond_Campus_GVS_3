package de.fhws.fiw.fds.beyondcampus.client.web;

import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;

import java.io.IOException;

public class PartnerUniModuleWebClient{
    private final GenericWebClient<ModuleClientModel> client;

    public PartnerUniModuleWebClient(){
        this.client=new GenericWebClient<>();
    }

    public PartnerUniModuleWebResponse getSingleModuleOfPartnerUni(String url) throws IOException{
        return createResponse(this.client.sendGetSingleRequest(url,ModuleClientModel.class));
    }

    public PartnerUniModuleWebResponse getModuleCollectionOfPartnerUnis(String url) throws IOException{
        return createResponse(this.client.sendGetCollectionRequest(url,ModuleClientModel.class));
    }

    public PartnerUniModuleWebResponse postNewModuleOfPartnerUni(String url,ModuleClientModel module) throws IOException{
        return createResponse(this.client.sendPostRequest(url,module));
    }

    public int extractIdFromUrl(String url) {
        int lastSlashIndex = url.lastIndexOf('/');
        return Integer.parseInt(url.substring(lastSlashIndex + 1));
    }

    public PartnerUniModuleWebResponse putModuleOfPartnerUni(String url,ModuleClientModel module) throws IOException{
        int id = extractIdFromUrl(url);
        module.setId(id);
        return createResponse(this.client.sendPutRequest(url,module));
    }

    public PartnerUniModuleWebResponse deleteModuleOfPartnerUni(String url) throws IOException{
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PartnerUniModuleWebResponse resetDatabaseONServer(String url) throws IOException{
        return createResponse(this.client.sendGetSingleRequest(url+"/resetdatabase"));
    }

    private PartnerUniModuleWebResponse createResponse(WebApiResponse<ModuleClientModel> response){
        return new PartnerUniModuleWebResponse(response.getResponseData(),response.getResponseHeaders(),response.getLastStatusCode());
    }
}
