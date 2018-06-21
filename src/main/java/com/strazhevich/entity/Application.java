package com.strazhevich.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;

    @Column(name = "request")
    private String request;

    @Column(name = "bid")
    private double bid;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status = "not completed";

    public Application() {
    }

    public Application(String request, double bid, Date date, User user) {
        this.request = request;
        this.bid = bid;
        this.date = date;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", request='" + request + '\'' +
                ", bid=" + bid +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
