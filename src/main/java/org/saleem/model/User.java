package org.saleem.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"createdAt"})
public class User {

    private String id;
    private String name;
    private String job;
}
