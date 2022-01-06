/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.parsers.rss;

import hr.myProject.enumeration.TagType;
import hr.myProject.model.Movie;
import hr.myProject.model.Person;
import hr.myUilities.factory.ParserFactory;
import hr.myUilities.factory.UrlConnectionFactory;
import hr.myUilities.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Nina
 */
public class MovieParser {
    
    /*private static final String RSS_URL="https://www.blitz-cinestar.hr/rss.aspx?najava=1";*/
    private static final String RSS_URL="https://www.blitz-cinestar.hr/rss.aspx?najava=2";
    private static final String EXT=".jpg";
    private static final String DIR="assets";
    private static final String DELIMITER=",";
    private static final String REGEX="\\<.*?>";

    private static void handlePicture(Movie movie, String url) throws IOException {
        try {
            String ext = url.substring(url.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;            
            String path = DIR + File.separator + pictureName;
            FileUtils.copyFromUrl(url, path);
            movie.setPicturePath(path);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Person getPerson(String data) {
        String[] personInfo = data.trim().split(" ", 2);
        switch(personInfo.length){
            case 1:
                return new Person(personInfo[0], "");
            case 2:
                return new Person(personInfo[0], personInfo[1]);
        }
        throw new IllegalArgumentException("Data could not be handled");
    }

    public MovieParser() {
    }
    
    public static List<Movie> parse() throws IOException, XMLStreamException, ParseException{
        
        List<Movie> movies= new ArrayList<>();
        
        HttpURLConnection con=UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        
        try(InputStream is=con.getInputStream()){
            XMLEventReader reader=ParserFactory.createStaxParser(is);
            
            StartElement startElement=null;
            Movie movie=null;
            Optional<TagType> tagType=Optional.empty();
            
            while (reader.hasNext()) {
                XMLEvent event=reader.nextEvent();
                
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        startElement=event.asStartElement();
                        String qName=startElement.getName().getLocalPart();
                        tagType=TagType.from(qName);
                        
                        if (tagType.isPresent() && tagType.get() == TagType.ITEM) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (tagType.isPresent()) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case HRTITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setHrTitle(data);
                                    }                                    
                                    break;
                                case PUBDATE:
                                    if (movie != null && !data.isEmpty()) {
                                    LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                    movie.setPublishedDate(publishedDate);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDescription(removeHtml(data));
                                    }                                    
                                    break;
                                case ENTITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setEnTitle(data);
                                    }                                    
                                    break;
                                case DIRECTOR:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDirector(getPerson(data));
                                    }                                    
                                    break;
                                case ACTOR:
                                    if (movie != null && !data.isEmpty()) {
                                        List<Person> people = new ArrayList<>();
                                        String[] peopleInfo = data.split(DELIMITER);
                                        for (String personInfo : peopleInfo) {
                                            people.add(getPerson(personInfo));
                                        }
                                        movie.setActors(people);
                                    }                                    
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setDuration(Integer.parseInt(data));
                                    }                                    
                                    break;
                                case GENRE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setGenres(data);
                                    }
                                    break;
                                case PICTURE:
                                    if (movie != null && !data.isEmpty()) {
                                    handlePicture(movie, data);
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }
        }
        return movies;
    }
    
    private static String removeHtml(String data){
        return data.replaceAll(REGEX, "");
    }
}
