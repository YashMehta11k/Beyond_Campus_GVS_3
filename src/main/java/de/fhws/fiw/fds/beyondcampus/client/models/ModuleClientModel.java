package de.fhws.fiw.fds.beyondcampus.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;

public class ModuleClientModel extends AbstractClientModel {

    public enum OfferedInSem {
        Autumn, Spring
    }

    private String modName;
    private String professorName;
    private int noOfCredits;
    private OfferedInSem offeredInSem;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLinkOnSecond;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLink;

    public ModuleClientModel() {}

    public ModuleClientModel(final String modName, final String professorName, final int noOfCredits, final OfferedInSem offeredInSem) {
        this.modName = modName;
        this.professorName = professorName;
        this.noOfCredits = noOfCredits;
        this.offeredInSem = offeredInSem;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public int getNoOfCredits() {
        return noOfCredits;
    }

    public void setNoOfCredits(int noOfCredits) {
        this.noOfCredits = noOfCredits;
    }

    public OfferedInSem getOfferedInSem() {
        return offeredInSem;
    }

    public void setOfferedInSem(OfferedInSem offeredInSem) {
        this.offeredInSem = offeredInSem;
    }

    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    @Override
    public String toString() {
        return "ModuleClientModel{" +
                "id=" + id +
                ", modName='" + modName + '\'' +
                ", professorName='" + professorName + '\'' +
                ", noOfCredits=" + noOfCredits + '\'' +
                ", offeredInSem=" + offeredInSem + '\'' +
                '}';
    }
}
