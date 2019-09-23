package br.com.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.place.model.Place;
import br.com.place.repository.PlaceRepository;


@SpringBootApplication
public class PlaceServiceApplication {

    @Autowired
    private PlaceRepository repository;
    
	public static void main(String[] args) {
		SpringApplication.run(PlaceServiceApplication.class, args);
	}
	
    public void run(String... strings) throws Exception {
        repository.deleteAll();
        Place placeA = new Place();
        placeA.setCity("Rio de Janeiro");
        placeA.setName("Bangu");
        placeA.setSlug("Lesma");
        placeA.setState("RJ");
        
        repository.save(placeA);

        System.out.println("Place found : ");

        repository.findAll().forEach(System.out::println);

        System.out.println();

        System.out.println("Customer found by first name: (Erie)");
        System.out.println("----------------");
        System.out.println(repository.findByName("Bangu"));


    }

}
