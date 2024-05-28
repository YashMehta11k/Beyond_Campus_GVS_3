package de.fhws.fiw.fds.beyondcampus.client.web;

import de.fhws.fiw.fds.beyondcampus.client.models.PartnerUniClientModel;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import okhttp3.Headers;

import java.util.Collection;

public class PartnerUniWebResponse extends WebApiResponse<PartnerUniClientModel> {

    public PartnerUniWebResponse(final Collection<PartnerUniClientModel> responseData, final Headers headers,final int lastStatusCode){
        super(responseData,headers,lastStatusCode);
    }
}
