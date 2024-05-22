package de.fhws.fiw.fds.beyondcampus.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.beyondcampus.client.models.ModuleClientModel;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SecondarySelfLink;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SelfLink;
import jakarta.xml.bind.annotation.XmlRootElement;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

@JsonRootName("modules")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name="modules")
public class Module extends AbstractModel{

    public enum OfferedInSem {
        Autumn, Spring
    }
    private String modName;
    private String professorName;
    private int noOfCredits;
    private OfferedInSem offeredInSem;

    @SecondarySelfLink(
            primaryPathElement = "partnerunis",
            secondaryPathElement = "modules"
    )
    private transient Link selfLinkOnSecond;

    @SelfLink(
            pathElement = "modules"
    )
    private transient Link selfLink;

    public Module() {}

    public Module(final String modName, final String professorName, final int noOfCredits, final OfferedInSem offeredInSem) {
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
