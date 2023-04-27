package com.reactiveproject.sport.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class Sport {
    @Id
    private long id;
    private String name;
}
