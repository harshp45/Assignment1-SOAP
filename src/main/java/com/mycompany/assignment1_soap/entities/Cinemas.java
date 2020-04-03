/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.assignment1_soap.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Harsh
 */
@Entity
@Table(name = "CINEMAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cinemas.findAll", query = "SELECT c FROM Cinemas c"),
    @NamedQuery(name = "Cinemas.findById", query = "SELECT c FROM Cinemas c WHERE c.id = :id"),
    @NamedQuery(name = "Cinemas.findByTitle", query = "SELECT c FROM Cinemas c WHERE c.title = :title"),
    @NamedQuery(name = "Cinemas.findByDirector", query = "SELECT c FROM Cinemas c WHERE c.director = :director"),
    @NamedQuery(name = "Cinemas.findByProducer", query = "SELECT c FROM Cinemas c WHERE c.producer = :producer"),
    @NamedQuery(name = "Cinemas.findByRunningTime", query = "SELECT c FROM Cinemas c WHERE c.runningTime = :runningTime")})
public class Cinemas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 100)
    @Column(name = "DIRECTOR")
    private String director;
    @Size(max = 100)
    @Column(name = "PRODUCER")
    private String producer;
    @Size(max = 100)
    @Column(name = "RUNNING_TIME")
    private String runningTime;
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    public Cinemas() {
    }

    public Cinemas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    
    @Override
    public String toString() {
        return "com.mycompany.assignment1_soap.entities.Cinemas[ id=" + id + " ]";
    }
    
}
