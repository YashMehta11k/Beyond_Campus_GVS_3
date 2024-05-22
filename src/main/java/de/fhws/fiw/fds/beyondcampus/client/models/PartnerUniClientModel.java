package de.fhws.fiw.fds.beyondcampus.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import jakarta.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.fhws.fiw.fds.sutton.client.utils.Link;

import java.time.LocalDate;

@XmlRootElement
public class PartnerUniClientModel extends AbstractClientModel {

    private String uniName;
    private String uniCountry;
    private String departmentName;
    private String departmentWebsite;
    private String uniContactPerson;

    private int maxIncomingStudents;
    private int maxOutgoingStudents;

    private LocalDate upcomingSpringSem= LocalDate.of(2025,3,1);
    private LocalDate upcomingAutumnSem= LocalDate.of(2025,9,1);

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link module;

    public PartnerUniClientModel() {}

    public PartnerUniClientModel(final String uniName,final String uniCountry,final String uniContactPerson,
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

    @JsonIgnore
    public Link getSelfLink(){
        return selfLink;
    }

    public void setSelfLink(Link selfLink){
        this.selfLink=selfLink;
    }

    @JsonIgnore
    public Link getModule(){
        return module;
    }

    public void setModule(Link module){
        this.module=module;
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
