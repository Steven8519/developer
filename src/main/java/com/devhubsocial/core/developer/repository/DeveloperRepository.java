package com.devhubsocial.core.developer.repository;

import com.devhubsocial.core.developer.domain.Developer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface DeveloperRepository extends ReactiveMongoRepository<Developer, String > {

    Mono<Developer> findDeveloperByTypeOfDeveloper(String typeOfDeveloper);
}
