package com.devhubsocial.core.developer.repository;

import com.devhubsocial.core.developer.domain.Developer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends ReactiveMongoRepository<Developer, String > {
}
