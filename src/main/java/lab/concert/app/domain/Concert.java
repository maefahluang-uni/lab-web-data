package lab.concert.app.domain;



import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class to represent a Concert. A Concert is characterised by an unique ID,
 * title, date and time, and a featuring Performer.
 * <p>
 * Concert implements Comparable with a natural ordering based on its title.
 * Hence, in a List, Concert instances can be sorted into alphabetical order
 * based on their title value.
 */
//TODO: Add annotations to map this class to the database
public class Concert {

   //TODO: Add annotations 
    private Long id;
    private String title;
    private LocalDateTime date;

    //TODO: Add annotations to map relationship
    private Performer performer;

    public Concert(Long id, String title, LocalDateTime date, Performer performer) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.performer = performer;
    }

    public Concert(String title, LocalDateTime date, Performer performer) {
        this(null, title, date, performer);
    }

    public Concert() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Performer getPerformer() {
        return performer;
    }

}
