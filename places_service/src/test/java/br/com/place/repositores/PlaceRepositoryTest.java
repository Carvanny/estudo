package br.com.place.repositores;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.BasicDBObject;

import br.com.place.model.Place;
import br.com.place.repository.PlaceRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceRepositoryTest {

	@Autowired
	private PlaceRepository repository;

    @Test
    public void readsFirstPageCorrectly() {

      Page<Place> places = repository.findAll(new PageRequest(0, 10));
      assertThat(places.isFirst(), is(true));
    }
    
	@Before
	public void setUp() throws Exception {
//        Place placeA = new Place();
//        placeA.setCity("Rio de Janeiro");
//        //placeA.setId(new Long(1234));
//        placeA.setName("Bangu");
//        placeA.setSlug("Lesma");
//        placeA.setState("RJ");
//               
//        repository.save(placeA);
		
		Place place1 = new Place();
		Place place2 = new Place();
		
		// save product, verify has ID value after save
		assertNull(place1.getId());
		assertNull(place2.getId());// null before save
		this.repository.save(place1);
		this.repository.save(place2);
		assertNotNull(place1.getId());
		assertNotNull(place2.getId());
	}

	@Test
	public void testFetchData() {        
		/* Test data retrieval */
		Place place1 = repository.findByName("Barra");
		assertNotNull(place1);
		assertEquals("RJ", place1.getState());
		/* Get all products, list should only have two */
//		Iterable<Place> places = repository.findAll();
//		int count = 0;
//		for (Place p : places) {
//			count++;
//		}
//		assertEquals(count, 23);
	}

//	@Test
//	public void testDataUpdate() {
//		/* Test update */
//		Place placeB = repository.findByName("Bangu");
//		placeB.setState("RJ");
//		repository.save(placeB);
//		Place placeC = repository.findByName("Tijuca");
//		assertNotNull(placeC);
//		assertEquals("Rio de Janeiro", placeC.getCity());
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		this.repository.deleteAll();
//	}
}
