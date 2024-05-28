package de.fhws.fiw.fds.beyondcampus.client.web;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.beyondcampus.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;

import java.io.IOException;

public class PartnerUniWebClient {

    private GenericWebClient<PartnerUniClientModel> client;

    public PartnerUniWebClient(){
        this.client=new GenericWebClient<>();
    }

    public PartnerUniWebResponse getDispatcher(String url) throws IOException{
        return createResponse(this.client.sendGetSingleRequest(url));
    }

    public PartnerUniWebResponse getSinglePartnerUni(String url) throws IOException{
        return createResponse(this.client.sendGetSingleRequest(url,PartnerUniClientModel.class));
    }

    public PartnerUniWebResponse getCollectionOfPartnerUnis(String url) throws IOException{
        return createResponse(this.client.sendGetCollectionRequest(url,PartnerUniClientModel.class));
    }

    public PartnerUniWebResponse postNewPartnerUni(String url,PartnerUniClientModel partnerUni) throws IOException{
        return createResponse(this.client.sendPostRequest(url,partnerUni));
    }

    public PartnerUniWebResponse putPartnerUni(String url,PartnerUniClientModel partnerUni) throws IOException{
        return createResponse(this.client.sendPutRequest(url,partnerUni));
    }

    public PartnerUniWebResponse deletePartnerUni(String url) throws IOException{
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PartnerUniWebResponse resetDatabaseONServer(String url) throws IOException{
        return createResponse(this.client.sendGetSingleRequest(url+"/resetdatabase"));
    }

    private PartnerUniWebResponse createResponse(WebApiResponse<PartnerUniClientModel> response){
        return new PartnerUniWebResponse(response.getResponseData(),response.getResponseHeaders(),response.getLastStatusCode());
    }
}
