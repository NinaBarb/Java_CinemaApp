/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Nina
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"publishedDate", "hrTitle", "enTitle", "duration", "genres", "director", "actors", "description", "picturePath"})
public class Movie {
    
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    @XmlAttribute
    private int id;
    private String hrTitle;
    @XmlElement(name = "publisheddate")
    @XmlJavaTypeAdapter(PublishedDataAdapter.class)
    private LocalDateTime publishedDate;
    private String description;
    @XmlElement(name = "originaltitle")
    private String enTitle;
    private Person director;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Person> actors;
    private int duration;
    private String genres;
    @XmlElement(name = "picturepath")
    private String picturePath;

    public Movie() {
    }

    public Movie(int id, String hrTitle, LocalDateTime publishedDate, String description, String enTitle, Person director, List<Person> actors, int duration, String genres, String picturePath) {
        this(hrTitle, publishedDate, description, enTitle, director, actors, duration, genres, picturePath);
        this.id = id;
    }

    public Movie(String hrTitle, LocalDateTime publishedDate, String description, String enTitle, Person director, List<Person> actors, int duration, String genres, String picturePath) {
        this.hrTitle = hrTitle;
        this.publishedDate = publishedDate;
        this.description = description;
        this.enTitle = enTitle;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
        this.genres = genres;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public String getHrTitle() {
        return hrTitle;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() { 
        return description;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public Person getDirector() {
        return director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenres() {
        return genres;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setHrTitle(String hrTitle) {
        this.hrTitle = hrTitle;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return id + " - " + hrTitle;
    }
    
}
