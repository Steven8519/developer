package com.devhubsocial.core.developer.repository;

import com.devhubsocial.core.developer.domain.Developer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
public class DeveloperRepositoryTest {

    @Autowired
    DeveloperRepository developerRepository;

    List<Developer> developersList =
            Arrays.asList(new Developer("1", "Mark", "Stevens", "Python Developer"));

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
    public void get_all_developers_test() {
        StepVerifier.create(developerRepository.findAll())
            .expectSubscription()
            .expectNextCount(1)
            .expectComplete()
            .verify();

    }
}
