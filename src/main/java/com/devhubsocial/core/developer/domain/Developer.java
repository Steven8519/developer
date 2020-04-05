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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeOfDeveloper() {
        return typeOfDeveloper;
    }

    public void setTypeOfDeveloper(String typeOfDeveloper) {
        this.typeOfDeveloper = typeOfDeveloper;
    }


}
