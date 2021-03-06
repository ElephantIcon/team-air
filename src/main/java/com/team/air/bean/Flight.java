package com.team.air.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//航班
public class Flight {

    private Integer flight_id;
    private String origin;
    private String destination;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String start_time;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String end_time;
    private Integer status;
    private Double price;

    @Override
    public String toString() {
        return "Flight{" +
                "f_id=" + flight_id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", status=" + status +
                '}';
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
