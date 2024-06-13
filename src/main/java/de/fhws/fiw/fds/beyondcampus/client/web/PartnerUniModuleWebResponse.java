package de.fhws.fiw.fds.beyondcampus.client.web;

import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import okhttp3.Headers;

import java.util.Collection;

public class PartnerUniModuleWebResponse extends WebApiResponse<ModuleClientModel> {

    public PartnerUniModuleWebResponse(final Collection<ModuleClientModel> responseData, final Headers headers,final int lastStatusCode){
        super(responseData,headers,lastStatusCode);
    }
}
