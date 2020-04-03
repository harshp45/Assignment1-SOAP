/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mycompany.assignment1_soap.entities.Cinemas;
import com.mycompany.assignment1_soap.entities.CinemasJpaController;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author Harsh
 */
@WebService(serviceName = "CinemaUpload")
@MTOM(enabled = true, threshold = 100000)
public class CinemaUpload {

    @WebMethod(operationName = "listCinemas")
    public ArrayList<Cinemas> listCinemas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        CinemasJpaController cinemaRepo = new CinemasJpaController(emf);

        ArrayList<Cinemas> cinema = new ArrayList<>();

        cinemaRepo.findCinemasEntities().forEach(c -> {
            cinema.add(c);
        });

        return cinema;
    }

    @WebMethod(operationName = "addCinema")
    public void addCinema(Cinemas c) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        CinemasJpaController cinemaRepo = new CinemasJpaController(emf);

        Cinemas cinema = new Cinemas();
        cinema.setId(c.getId());
        cinema.setTitle(c.getTitle());
        cinema.setProducer(c.getProducer());
        cinema.setDirector(c.getDirector());
        cinema.setRunningTime(c.getRunningTime());
        cinema.setImage(c.getImage());

        
        cinemaRepo.create(cinema);
    }
    
    @WebMethod(operationName = "destroyCinema")
    public void destroyCinema(Cinemas c) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        CinemasJpaController cinemaRepo = new CinemasJpaController(emf);

        cinemaRepo.destroy(c.getId());
    }
    
    @WebMethod(operationName = "updateCinema")
    public void updateFilm(Cinemas c) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        CinemasJpaController cinemaRepo = new CinemasJpaController(emf);

        Cinemas cinemaFetch = new Cinemas();
        cinemaFetch = cinemaRepo.findCinemas(c.getId());
        
        Cinemas cinemaEdit = new Cinemas();
        cinemaEdit.setId(cinemaFetch.getId());
        cinemaEdit.setTitle(c.getTitle());
        cinemaEdit.setProducer(c.getProducer());
        cinemaEdit.setDirector(c.getDirector());
        cinemaEdit.setRunningTime(c.getRunningTime());
        cinemaEdit.setImage(c.getImage());
        
        cinemaRepo.edit(cinemaEdit);
        
    }
}
