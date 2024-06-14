package de.fhws.fiw.fds.beyondcampus.client.rest;

import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.beyondcampus.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.beyondcampus.client.web.PartnerUniWebClient;
import de.fhws.fiw.fds.beyondcampus.client.web.PartnerUniModuleWebClient;
import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BeyondCampusRestClient extends AbstractRestClient {

    private static final String BASE_URL = "http://localhost:8080/beyondcampus/api";
    private static final String GET_ALL_PARTNERUNIS = "getAllPartnerUnis";
    private static final String CREATE_PARTNERUNI = "createPartnerUni";
    private static final String UPDATE_PARTNERUNI = "updatePartnerUni";
    private static final String DELETE_PARTNERUNI = "deletePartnerUni";

    private static final String GET_ALL_MODULES = "getAllModulesOfPartnerUni";
    private static final String CREATE_MODULE = "createModuleOfPartnerUni";
    private static final String UPDATE_MODULE = "updateModuleOfPartnerUni";
    private static final String DELETE_MODULE = "deleteModuleOfPartnerUni";

    private List<PartnerUniClientModel> currentPartnerUniData;
    private int cursorPartnerUniData = 0;

    private List<ModuleClientModel> currentModuleData;
    private int cursorModuleData = 0;

    final private PartnerUniWebClient partnerUniClient;
    final private PartnerUniModuleWebClient moduleClient;

    public BeyondCampusRestClient() {
        super();
        this.partnerUniClient = new PartnerUniWebClient();
        this.moduleClient = new PartnerUniModuleWebClient();
        this.currentPartnerUniData = Collections.emptyList();
        this.currentModuleData = Collections.emptyList();
    }

    public void resetDatabase() throws IOException {
        processResponse(this.partnerUniClient.resetDatabaseONServer(BASE_URL), (response) -> {});
    }

    public void start() throws IOException {
        processResponse(this.partnerUniClient.getDispatcher(BASE_URL), (response) -> {});
    }

    public boolean isCreatePartnerUniAllowed() {
        return isLinkAvailable(CREATE_PARTNERUNI);
    }

    public boolean isUpdatePartnerUniAllowed() {
        return isLinkAvailable(UPDATE_PARTNERUNI);
    }

    public boolean isDeletePartnerUniAllowed() {
        return isLinkAvailable(DELETE_PARTNERUNI);
    }

    public void createPartnerUni(PartnerUniClientModel partnerUni) throws IOException {
        if (isCreatePartnerUniAllowed()) {
            processResponse(this.partnerUniClient.postNewPartnerUni(getUrl(CREATE_PARTNERUNI), partnerUni), (response) -> {
                this.currentPartnerUniData = Collections.emptyList();
                this.cursorPartnerUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllPartnerUnisAllowed() {
        return isLinkAvailable(GET_ALL_PARTNERUNIS);
    }

    public void getAllPartnerUnis() throws IOException {
        if (isGetAllPartnerUnisAllowed()) {
            processResponse(this.partnerUniClient.getCollectionOfPartnerUnis(getUrl(GET_ALL_PARTNERUNIS)), (response) -> {
                this.currentPartnerUniData = new LinkedList<>(response.getResponseData());
                this.cursorPartnerUniData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSinglePartnerUniAllowed() {
        return !this.currentPartnerUniData.isEmpty() || isLocationHeaderAvailable();
    }

    public List<PartnerUniClientModel> partnerUniData() {
        if (this.currentPartnerUniData.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.currentPartnerUniData;
    }

    public void setPartnerUniCursor(int index) {
        if (0 <= index && index < this.currentPartnerUniData.size()) {
            this.cursorPartnerUniData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getSinglePartnerUni() throws IOException {
        if (isLocationHeaderAvailable()) {
            getSinglePartnerUni(getLocationHeaderURL());
        } else if (!this.currentPartnerUniData.isEmpty()) {
            getSinglePartnerUni(this.cursorPartnerUniData);
        } else {
            throw new IllegalStateException();
        }
    }

    public void getSinglePartnerUni(int index) throws IOException {
        getSinglePartnerUni(this.currentPartnerUniData.get(index).getSelfLink().getUrl());
    }

    public void getSinglePartnerUni(String url) throws IOException {
        processResponse(this.partnerUniClient.getSinglePartnerUni(url), (response) -> {
            this.currentPartnerUniData = new LinkedList<>(response.getResponseData());
            this.cursorPartnerUniData = 0;
        });
    }

    public void updatePartnerUni(PartnerUniClientModel partnerUni) throws IOException {
        if (isUpdatePartnerUniAllowed()) {
            String url = this.currentPartnerUniData.get(this.cursorPartnerUniData).getSelfLink().getUrl();
            processResponse(this.partnerUniClient.putPartnerUni(url, partnerUni), (response) -> {
                this.currentPartnerUniData.set(this.cursorPartnerUniData, partnerUni);
                this.cursorPartnerUniData = 0;
            });
        }
    }

    public void deletePartnerUni() throws IOException {
        if (isDeletePartnerUniAllowed()) {
            String url = this.currentPartnerUniData.get(this.cursorPartnerUniData).getSelfLink().getUrl();
            processResponse(this.partnerUniClient.deletePartnerUni(url), (response) -> {
                if (this.cursorPartnerUniData >= this.currentPartnerUniData.size()) {
                    this.cursorPartnerUniData = this.currentPartnerUniData.size() - 1;
                }
            });
        }
    }

    // Module Operations

    public boolean isCreateModuleAllowed() {
        return isLinkAvailable(CREATE_MODULE);
    }

    public boolean isUpdateModuleAllowed() {
        return isLinkAvailable(UPDATE_MODULE);
    }

    public boolean isDeleteModuleAllowed() {
        return isLinkAvailable(DELETE_MODULE);
    }

    public void createModule(ModuleClientModel module) throws IOException {
        if (isCreateModuleAllowed()) {
            processResponse(this.moduleClient.postNewModuleOfPartnerUni(getUrl(CREATE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.emptyList();
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllModulesAllowed() {
        return isLinkAvailable(GET_ALL_MODULES);
    }

    public void getAllModules() throws IOException {
        if (isGetAllModulesAllowed()) {
            processResponse(this.moduleClient.getModuleCollectionOfPartnerUnis(getUrl(GET_ALL_MODULES)), (response) -> {
                this.currentModuleData = new LinkedList<>(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSingleModuleAllowed() {
        return !this.currentModuleData.isEmpty() || isLocationHeaderAvailable();
    }

    public List<ModuleClientModel> moduleData() {
        if (this.currentModuleData.isEmpty()) {
            throw new IllegalStateException();
        }
        return this.currentModuleData;
    }

    public void setModuleCursor(int index) {
        if (0 <= index && index < this.currentModuleData.size()) {
            this.cursorModuleData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getSingleModule() throws IOException {
        if (isLocationHeaderAvailable()) {
            getSingleModule(getLocationHeaderURL());
        } else if (!this.currentModuleData.isEmpty()) {
            getSingleModule(this.cursorModuleData);
        } else {
            throw new IllegalStateException();
        }
    }

    public void getSingleModule(int index) throws IOException {
        getSingleModule(this.currentModuleData.get(index).getSelfLink().getUrl());
    }

    public void getSingleModule(String url) throws IOException {
        processResponse(this.moduleClient.getSingleModuleOfPartnerUni(url), (response) -> {
            this.currentModuleData = new LinkedList<>(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }

    public void updateModule(ModuleClientModel module) throws IOException {
        String url = this.currentModuleData.get(this.cursorModuleData).getSelfLinkOnSecond().getUrl();
        if (isUpdateModuleAllowed()) {
            processResponse(this.moduleClient.putModuleOfPartnerUni(url, module), (response) -> {
                this.currentModuleData.set(this.cursorModuleData, module);
                this.cursorModuleData = 0;
            });
        }
    }

    public void deleteModule() throws IOException {
        if (isDeleteModuleAllowed()) {
            String url = this.currentModuleData.get(this.cursorModuleData).getSelfLinkOnSecond().getUrl();
            processResponse(this.moduleClient.deleteModuleOfPartnerUni(url), (response) -> {
                if (this.cursorModuleData >= this.currentModuleData.size()) {
                    this.cursorModuleData = this.currentModuleData.size() - 1;
                }
            });
        }
    }
}
