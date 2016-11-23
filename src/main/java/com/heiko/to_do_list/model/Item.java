package com.heiko.to_do_list.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tasks")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "alert")
    private Date alert;

    @Column(name = "state")
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Item() {

    }

    public Item(String title, String description, Date... dates) {
        this.title = title;
        this.description = description;
        this.deadline = dates[0];
        if (dates.length > 1)
            this.alert = dates[1];
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getAlert() {
        return alert;
    }

    public void setAlert(Date alert) {
        this.alert = alert;
    }
}
