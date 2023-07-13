package com.example.bikeevents.dto;

import java.util.Date;

public class RideDto {
    private int id;
    private String name;
    private Date start_date;
    private Date start_date_registration;
    private Date end_date_registration;
    private String additional_information;
    private String start_place;
    private int participants_limit;
    private int client_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getStart_date_registration() {
        return start_date_registration;
    }

    public void setStart_date_registration(Date start_date_registration) {
        this.start_date_registration = start_date_registration;
    }

    public Date getEnd_date_registration() {
        return end_date_registration;
    }

    public void setEnd_date_registration(Date end_date_registration) {
        this.end_date_registration = end_date_registration;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }

    public String getStart_place() {
        return start_place;
    }

    public void setStart_place(String start_place) {
        this.start_place = start_place;
    }

    public int getParticipants_limit() {
        return participants_limit;
    }

    public void setParticipants_limit(int participants_limit) {
        this.participants_limit = participants_limit;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}
