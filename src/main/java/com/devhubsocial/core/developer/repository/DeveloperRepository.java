package com.devhubsocial.core.developer.repository;

import com.devhubsocial.core.developer.domain.Developer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DeveloperRepository extends ReactiveMongoRepository<Developer, String> {
}
