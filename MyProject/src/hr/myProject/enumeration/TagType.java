/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.enumeration;

import java.util.Optional;

/**
 *
 * @author Nina
 */
public enum TagType {
    
    ITEM("item"),
    HRTITLE("title"),
    PUBDATE("pubDate"),
    DESCRIPTION("description"),
    ENTITLE("orignaziv"),
    DIRECTOR("redatelj"),
    ACTOR("glumci"),
    DURATION("trajanje"),
    GENRE("zanr"),
    PICTURE("plakat");
    
    private final String name;

    private TagType(String name) {
        this.name = name;
    }
    
    public static Optional<TagType> from(String name){
        for (TagType value:values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
    
}
