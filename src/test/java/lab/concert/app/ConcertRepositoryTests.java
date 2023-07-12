package lab.concert.app;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;
import java.util.Iterator;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.jdbc.Sql;

import lab.concert.app.Concert;
import lab.concert.app.ConcertRepository;
import lab.concert.app.Performer;

@DataJpaTest
public class ConcertRepositoryTests {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private ConcertRepository concertRepository;



    @Test
    public void queryConcert() {
        int num = 0;
        Iterator<Concert> concerts =  concertRepository.findAll().iterator();
        while(concerts.hasNext()) {
            num++;
            concerts.next();
        }
        assertEquals(22, num);
    
        Concert concert1 = concertRepository.findById(1L).get();
        Concert concert2 = concertRepository.findById(1L).get();
        // The DAO is expected to return 2 Concert objects with the same
        // value.
        assertEquals(concert1, concert2);

        // The DAO is expected to return the same Concert.
        assertSame(concert1, concert2);
    }

    @Test
    public void addConcert() {

        // Retrieve Ed Sheran's concert "Divide Tour", and the extract the
        // Performer object for Ed.
        Concert divideTour = em.find(Concert.class, 2L);
        Performer ed = divideTour.getPerformer();

        // Create a new Concert featuring Ed.
        LocalDateTime date = LocalDateTime.of(2017, 12, 1, 16, 00);
        System.out.println(date.toString());
        Concert garbage = new Concert("My Music is Dreadful", date, ed);

        // Save the new Concert.
        garbage = em.persist(garbage);

        // Query all Concerts and pick out the new Concert.
        Concert concert = em.find(Concert.class, garbage.getId());

        // Check that the new Concert's ID has been assigned.
        assertNotNull(concert.getId());

        // Check that the result of the query for the new Concert equals
        // the newly created Concert.
        assertEquals(garbage, concert);

    }

    @Test
    public void deleteConcert() {

        // Query Concert with the ID 18 (this represents "Evolve!").
        Concert evolve = em.find(Concert.class, 18L);

        // Delete the Concert.
        em.remove(evolve);

        // Requery the Concert to check it's been deleted.
        evolve = em.find(Concert.class, 18L);
        assertNull(evolve);
    }

    @Test
    public void updateConcertAndPerformer() {
        // Query Concert with the ID 11 (this represents "Dangerous Woman").
        Concert dangerousWoman = em.find(Concert.class, 11L);

        // Update the Concert's date, postponing it by one week.
        LocalDateTime newDate = LocalDateTime.of(2017, 8, 17, 18, 30);
        dangerousWoman.setDate(newDate);

        // Also change the Concert Performer's image file.
        Performer performer = dangerousWoman.getPerformer();
        String newImage = "new_image.jpg";
        performer.setImageUri(newImage);

        // Save the updated Concert (and Performer).
        em.persist(dangerousWoman);

        // Requery the Concert.
        Concert concert = em.find(Concert.class, 11L);

        // Check that the Concert's date has been updated.
        assertEquals(newDate, concert.getDate());

        // Check that the Performer's been updated too.
        performer = concert.getPerformer();
        assertEquals(newImage, performer.getImageUri());

    }
}
