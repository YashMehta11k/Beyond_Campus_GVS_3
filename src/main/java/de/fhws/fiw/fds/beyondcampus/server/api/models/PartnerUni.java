package de.fhws.fiw.fds.beyondcampus.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@JsonRootName("partnerunis")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name="partnerunis")
public class PartnerUni extends AbstractModel {

    private String uniName;
    private String uniCountry;
    private String uniContactPerson;

    private String departmentName;
    private String departmentWebsite;

    private int maxIncomingStudents;
    private int maxOutgoingStudents;

    private LocalDate upcomingSpringSem;
    private LocalDate upcomingAutumnSem;

    @SuttonLink(
            value = "partnerunis/${id}",
            rel = "self"
    )
    private transient Link selfLink;

    @SuttonLink(
            value = "partnerunis/${id}/modules",
            rel = "getUniAllModules"
    )
    private transient Link module;

    public PartnerUni() {}

    public PartnerUni(final String uniName,final String uniCountry,final String uniContactPerson,
                                 final String departmentName,final String departmentWebsite,
                                 final int maxIncomingStudents,final int maxOutgoingStudents,
                                 final LocalDate upcomingAutumnSem,final LocalDate upcomingSpringSem){

        this.uniName=uniName;
        this.uniCountry=uniCountry;
        this.uniContactPerson=uniContactPerson;
        this.departmentName=departmentName;
        this.departmentWebsite=departmentWebsite;
        this.maxIncomingStudents=maxIncomingStudents;
        this.maxOutgoingStudents=maxOutgoingStudents;
        this.upcomingAutumnSem=upcomingAutumnSem;
        this.upcomingSpringSem=upcomingSpringSem;
    }

    public Link getSelfLink(){
        return selfLink;
    }
    public void setSelfLink(Link selfLink){
        this.selfLink=selfLink;
    }

    public Link getModule(){
        return module;
    }
    public void setModule(Link module){
        this.module=module;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniCountry() {
        return uniCountry;
    }

    public void setUniCountry(String uniCountry) {
        this.uniCountry = uniCountry;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentWebsite() {
        return departmentWebsite;
    }

    public void setDepartmentWebsite(String departmentWebsite) {
        this.departmentWebsite = departmentWebsite;
    }

    public String getUniContactPerson() {
        return uniContactPerson;
    }

    public void setUniContactPerson(String uniContactPerson) {
        this.uniContactPerson = uniContactPerson;
    }

    public int getMaxIncomingStudents() {
        return maxIncomingStudents;
    }

    public void setMaxIncomingStudents(int maxIncomingStudents) {
        this.maxIncomingStudents = maxIncomingStudents;
    }

    public int getMaxOutgoingStudents() {
        return maxOutgoingStudents;
    }

    public void setMaxOutgoingStudents(int maxOutgoingStudents) {
        this.maxOutgoingStudents = maxOutgoingStudents;
    }

    public LocalDate getUpcomingSpringSem() {
        return upcomingSpringSem;
    }

    public void setUpcomingSpringSem(LocalDate upcomingSpringSem) {
        this.upcomingSpringSem = upcomingSpringSem;
    }

    public LocalDate getUpcomingAutumnSem() {
        return upcomingAutumnSem;
    }

    public void setUpcomingAutumnSem(LocalDate upcomingAutumnSem) {
        this.upcomingAutumnSem = upcomingAutumnSem;
    }

    @Override
    public String toString() {
        return "PartnerUniversity{" +
                "id=" + id +
                ", uniName='" + uniName + '\'' +
                ", uniCountry='" + uniCountry + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentWebsite='" + departmentWebsite + '\'' +
                ", uniContactPerson='" + uniContactPerson + '\'' +
                ", maxIncomingStudents=" + maxIncomingStudents + '\''+
                ", maxOutgoingStudents=" + maxOutgoingStudents + '\''+
                ", upcomingSpringSem=" + upcomingSpringSem + '\''+
                ", upcomingAutumnSem=" + upcomingAutumnSem + '\''+
                '}';
    }
}
