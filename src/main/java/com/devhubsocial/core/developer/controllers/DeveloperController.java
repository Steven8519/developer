package com.devhubsocial.core.developer.controllers;

import com.devhubsocial.core.developer.domain.Developer;
import com.devhubsocial.core.developer.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "api/v1/developers")
public class DeveloperController {

    private DeveloperRepository developerRepository;

    @Autowired
    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @GetMapping
    public Flux<Developer> getAllItems(){

        return developerRepository.findAll();

    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Developer>> getOneItem(@PathVariable String id){
        return  developerRepository.findById(id)
                .map((developer) -> new ResponseEntity<>(developer, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Developer> createItem(@RequestBody Developer developer){
        return developerRepository.save(developer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteItem(@PathVariable String id){

        return developerRepository.deleteById(id);

    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Developer>> updateItem(@PathVariable String id,
                                                 @RequestBody Developer developer){

        return developerRepository.findById(id)
                .flatMap(updatedDeveloper -> {

                    updatedDeveloper.setFirstName(developer.getFirstName());
                    updatedDeveloper.setLastName(developer.getLastName());
                    updatedDeveloper.setLastName(developer.getTypeOfDeveloper());
                    return developerRepository.save(updatedDeveloper);
                })
                .map(savedDeveloper -> new ResponseEntity<>(savedDeveloper, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
