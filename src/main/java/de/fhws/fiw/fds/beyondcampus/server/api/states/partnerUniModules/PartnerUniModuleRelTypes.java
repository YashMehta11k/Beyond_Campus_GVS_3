package de.fhws.fiw.fds.beyondcampus.server.api.states.partnerUniModules;

public interface PartnerUniModuleRelTypes {

    String CREATE_MODULE = "createModuleOfPartnerUni";
    String GET_SINGLE_MODULE = "getModuleOfPartnerUni";
    String GET_ALL_LINKED_MODULES = "getAllModulesOfPartnerUni";
    String GET_ALL_MODULES = "getAllLinkableModules";
    String UPDATE_SINGLE_MODULE = "updateModuleOfPartnerUni";
    String CREATE_LINK_FROM_PARTNERUNI_TO_MODULE = "linkPartnerUniToModule";
    String DELETE_LINK_FROM_PARTNERUNI_TO_MODULE = "unlinkPartnerUniToModule";
}
