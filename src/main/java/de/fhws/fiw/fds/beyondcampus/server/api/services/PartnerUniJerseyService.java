package de.fhws.fiw.fds.beyondcampus.server.api.services;

import de.fhws.fiw.fds.beyondcampus.server.api.models.Module;
import de.fhws.fiw.fds.beyondcampus.server.api.models.PartnerUni;
import de.fhws.fiw.fds.beyondcampus.server.api.queries.QueryByModOfferedInSem;
import de.fhws.fiw.fds.beyondcampus.server.api.queries.QueryByUniNameAndCountry;
import de.fhws.fiw.fds.beyondcampus.server.api.queries.QueryPartnerUniSearch;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules.*;
import de.fhws.fiw.fds.beyondcampus.server.api.states.partnerunis.*;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.Exceptions.SuttonWebAppException;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractJerseyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Objects;

@Path("partnerunis")
public class PartnerUniJerseyService extends AbstractJerseyService {

    public PartnerUniJerseyService(){
        super();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getAllPartnerUnis(
            @DefaultValue("") @QueryParam("uniName") final String uniName,
            @DefaultValue("") @QueryParam("uniCountry") final String uniCountry,
            @DefaultValue("+uniname") @QueryParam("order") final String order,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("10") @QueryParam("size") int size
            ){

        try{
                return new GetAllPartnerUnis(
                        this.serviceContext,
                        new QueryByUniNameAndCountry<>(uniName,uniCountry,order, offset, size)
                ).execute();

        }catch (SuttonWebAppException e){
            throw new WebApplicationException(e.getExceptionMessage(),e.getStatus().getCode());
        }
    }

    @GET
    @Path("{id:\\d+}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getSinglePartnerUni(@PathParam("id") final long id){
        try{
            return new GetSinglePartnerUni(this.serviceContext,id).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response createSinglePartnerUni(final PartnerUni partnerUniModel){
        try{
            return new PostNewPartnerUni(this.serviceContext,partnerUniModel).execute();
        }catch (SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{id:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateSinglePartnerUni(@PathParam("id") final long id,final PartnerUni partnerUniModel){

        try{
            return new PutSinglePartnerUni(this.serviceContext,id,partnerUniModel).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{id:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response deleteSinglePartnerUni(@PathParam("id") final long id){
        try{
            return new DeleteSinglePartnerUni(this.serviceContext,id).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{partneruniId:\\d+}/modules")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getModulesOfPartnerUni(@PathParam("partneruniId") final long partneruniId,
                                           @DefaultValue("") @QueryParam("offeredInSem") final String offeredInSem,
                                           @DefaultValue("false") @QueryParam("showAll") final boolean showAll,
                                           @DefaultValue("0") @QueryParam("offset") int offset,
                                           @DefaultValue("20") @QueryParam("size") int size){
        try {
            return new GetAllModulesofPartnerUni(this.serviceContext,partneruniId,new QueryByModOfferedInSem<>(partneruniId,offeredInSem,showAll,offset,size)).execute();
        }catch (SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @GET
    @Path("{partneruniId:\\d+}/modules/{moduleId:\\d+}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getModuleByIdOfPartnerUni(@PathParam("partneruniId") final long partneruniId,@PathParam("moduleId")final long moduleId){
        try{
            return new GetSingleModuleofPartnerUni(this.serviceContext,partneruniId,moduleId).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @POST
    @Path("{partneruniId:\\d+}/modules")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response createNewModuleOfPartnerUni(@PathParam("partneruniId") final long partneruniId, final Module module){
        try{
            return new PostNewModuleOfPartnerUni(this.serviceContext,partneruniId,module).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @PUT
    @Path("{partneruniId:\\d+}/modules/{moduleId:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateNewModuleOfPartnerUni(@PathParam("partneruniId") final long partneruniId,@PathParam("moduleId")final long moduleId,final Module module){
        try{
            return new PutSingleModuleOfPartnerUni(this.serviceContext,partneruniId,moduleId,module).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }

    @DELETE
    @Path("{partneruniId:\\d+}/modules/{moduleId:\\d+}")
    public Response deleteModuleOfPartnerUni(@PathParam("partneruniId") final long partneruniId,@PathParam("moduleId")final long moduleId){
        try{
            return new DeleteSingleModuleOfPartnerUni(this.serviceContext,partneruniId,moduleId).execute();
        }catch(SuttonWebAppException e){
            throw new WebApplicationException(Response.status(e.getStatus().getCode()).entity(e.getExceptionMessage()).build());
        }
    }
}
