package com.devhubsocial.core.developer.repository;

import com.devhubsocial.core.developer.domain.Developer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
@ActiveProfiles("dev")
public class DeveloperRepositoryTest {

    @Autowired
    DeveloperRepository developerRepository;

    List<Developer> developersList =
            Arrays.asList(new Developer(null, "Mark", "Stevens", "Python Developer"),
                            new Developer(null, "Sam", "Smith", "Java Developer"),
                             new Developer("1", "Chris", "Mathis", "React Developer"),
                             new Developer("2", "Lisa", "Chambers", "Google Cloud Developer"));

    @Before
    public void setUp(){

        developerRepository.deleteAll()
                .thenMany(Flux.fromIterable(developersList))
                .flatMap(developerRepository::save)
                .doOnNext((developer-> {
                    System.out.println("Inserted Developer is :" + developer);
                }))
                .blockLast();

    }

    @Test
    public void get_alldevelopers_test() {
        StepVerifier.create(developerRepository.findAll())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void get_developer_by_Id_test() {

        StepVerifier.create(developerRepository.findById("1"))
                .expectSubscription()
                .expectNextMatches((developer -> developer.getTypeOfDeveloper().equals("React Developer")))
                .verifyComplete();


    }

    @Test
    public void find_developer_by_developer_type_test() {

        StepVerifier.create(developerRepository.findDeveloperByTypeOfDeveloper("React Developer").log("findDeveloperByTypeOfDeveloper : "))
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void save_developer_test() {

        Developer developer = new Developer(null, "Mike", "Hanson", "Angular Developer");
        Mono<Developer> savedDeveloper = developerRepository.save(developer);
        StepVerifier.create(savedDeveloper.log("saveItem : "))
                .expectSubscription()
                .expectNextMatches(newDeveloper -> (newDeveloper.getId() != null && newDeveloper.getTypeOfDeveloper().equals("Angular Developer")))
                .verifyComplete();

    }

    @Test
    public void update_developer_test() {

        String newDeveloper = "AWS Cloud Developer";
        Mono<Developer> updatedDeveloper = developerRepository.findDeveloperByTypeOfDeveloper("Google Cloud Developer")
                .map(developer -> {
                    developer.setTypeOfDeveloper(newDeveloper); //setting the new price
                    return developer;
                })
                .flatMap((developer) -> {
                    return developerRepository.save(developer); //saving the item with the new price
                });

        StepVerifier.create(updatedDeveloper)
                .expectSubscription()
                .expectNextMatches(developer -> developer.getTypeOfDeveloper().equals("AWS Cloud Developer"))
                .verifyComplete();


    }


    @Test
    public void deleteItemById() {
        Mono<Void> deletedDeveloper = developerRepository.findById("1") // Mono<Developer>
                .map(Developer::getId) // get Id -> Transform from one type to another type
                .flatMap((id) -> {
                    return developerRepository.deleteById(id);
                });
        StepVerifier.create(deletedDeveloper.log())
                .expectSubscription()
                .verifyComplete();
        StepVerifier.create(developerRepository.findAll().log("The new Developer List : "))
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }
}
