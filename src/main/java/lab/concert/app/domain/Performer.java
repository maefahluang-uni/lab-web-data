package lab.concert.app.domain;


import javax.persistence.*;

/**
 * Class to represent a Performer (an artist or band that plays at Concerts). A
 * Performer object has an ID (a database primary key value), a name, the name
 * of an image file, and a genre.
 */
//TODO: add annotations to map this class to the database
public class Performer {

    //TODO: add annotations
    private Long id;
    private String name;
    private String imageUri;


    public Performer() { }

    public Performer(Long id, String name, String imageUri) {
        this.id = id;
        this.name = name;
        this.imageUri = imageUri;
    }

    public Performer(String name, String imageUri) {
        this(null, name, imageUri);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }




}
