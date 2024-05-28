package de.fhws.fiw.fds.beyondcampus.client.rest;

import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.beyondcampus.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.beyondcampus.client.web.PartnerUniWebClient;
import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BeyondCampusRestClient extends AbstractRestClient {

    private static final String BASE_URL="http://localhost:8080/beyondcampus/api";
    private static final String GET_ALL_PARTNERUNIS="getAllPartnerUnis";
    private static final String CREATE_PARTNERUNI="createPartnerUni";
    private static final String UPDATE_PARTNERUNI="updatePartnerUni";
    private static final String DELETE_PARTNERUNI="deletePartnerUni";
    private static final String GET_ALL_MODULES="getAllModules";

    private List<PartnerUniClientModel> currentPartnerUniData;
    private int cursorPartnerUniData =0;

    private List<ModuleClientModel> currentModuleData;
    private int cursorModuleData=0;

    final private PartnerUniWebClient client;

    public BeyondCampusRestClient(){
        super();
        this.client=new PartnerUniWebClient();
        this.currentPartnerUniData= Collections.EMPTY_LIST;
    }

    public void resetDatabase() throws IOException{
        processResponse(this.client.resetDatabaseONServer(BASE_URL),(response)->{});
    }

    public void start() throws IOException{
        processResponse(this.client.getDispatcher(BASE_URL),(response)->{});
    }

    public boolean isCreatePartnerUniAllowed(){
        return isLinkAvailable(CREATE_PARTNERUNI);
    }

    public boolean isUpdatePartnerUniAllowed(){
        return isLinkAvailable(UPDATE_PARTNERUNI);
    }

    public boolean isDeletePartnerUniAllowed(){
        return isLinkAvailable(DELETE_PARTNERUNI);
    }

    public void createPartnerUni(PartnerUniClientModel partnerUni) throws IOException{
        if(isCreatePartnerUniAllowed()){
            processResponse(this.client.postNewPartnerUni(getUrl(CREATE_PARTNERUNI),partnerUni),(response)->{
                this.currentPartnerUniData=Collections.EMPTY_LIST;
                this.cursorPartnerUniData =0;
            });
        }
    }

    public boolean isGetAllPartnerUnisAllowed(){
        return isLinkAvailable(GET_ALL_PARTNERUNIS);
    }

    public void getAllPartnerUnis() throws IOException{
        if(isGetAllPartnerUnisAllowed()){
            processResponse(this.client.getCollectionOfPartnerUnis(getUrl(GET_ALL_PARTNERUNIS)),(response)->{
                this.currentPartnerUniData=new LinkedList(response.getResponseData());
                this.cursorPartnerUniData =0;
            });
        }else{
            throw new IllegalStateException();
        }
    }

    public boolean isGetSinglePartnerUniAllowed(){
        return !this.currentPartnerUniData.isEmpty() || isLocationHeaderAvailable();
    }

    public List<PartnerUniClientModel> partnerUniData(){
        if(this.currentPartnerUniData.isEmpty()){
            throw new IllegalStateException();
        }
        return this.currentPartnerUniData;
    }

    public void setPartnerUniCursor(int index){
        if(0<=index && index<this.currentPartnerUniData.size()){
            this.cursorPartnerUniData =index;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void getSinglePartnerUni() throws IOException{
        if(isLocationHeaderAvailable()){
            getSinglePartnerUni(getLocationHeaderURL());
        }
        else if(!this.currentPartnerUniData.isEmpty()){
            getSinglePartnerUni(this.cursorPartnerUniData);
        }else{
            throw new IllegalStateException();
        }
    }

    public void getSinglePartnerUni(int index) throws IOException{
        getSinglePartnerUni(this.currentPartnerUniData.get(index).getSelfLink().getUrl());
    }

    public void getSinglePartnerUni(String url) throws IOException{
        processResponse(this.client.getSinglePartnerUni(url),(response)->{
            this.currentPartnerUniData=new LinkedList(response.getResponseData());
            this.cursorPartnerUniData =0;
        });
    }

    public void updatePartnerUni(int index,PartnerUniClientModel partnerUni) throws IOException{
        if(isUpdatePartnerUniAllowed()){
            String url=this.currentPartnerUniData.get(index).getSelfLink().getUrl();
            processResponse(this.client.putPartnerUni(url,partnerUni),(response)->{
                this.currentPartnerUniData.set(index,partnerUni);
                this.cursorPartnerUniData =0;
            });
        }else{
            throw new IllegalStateException();
        }
    }

    public void deletePartnerUni(int index) throws IOException{
        if(isDeletePartnerUniAllowed()){
            String url=this.currentPartnerUniData.get(index).getSelfLink().getUrl();
            processResponse(this.client.deletePartnerUni(url),(response)->{
                if (this.cursorPartnerUniData >= this.currentPartnerUniData.size()) {
                    this.cursorPartnerUniData = this.currentPartnerUniData.size() - 1;
                }
            });
        }else {
            throw new IllegalStateException();
        }
    }
}
