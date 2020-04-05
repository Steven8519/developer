package com.devhubsocial.core.developer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Developer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String typeOfDeveloper;
}
