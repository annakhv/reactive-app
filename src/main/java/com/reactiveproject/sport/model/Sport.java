package com.reactiveproject.sport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.web.reactive.function.server.ServerResponse;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Sport implements Persistable<Long>{
    @Id
    private  Long id;
    private  String name;
    @Transient
    @JsonIgnore
    private boolean newSport;

    public Sport(String name) {
        this.name = name;
        this.id=null;
    }

    public Sport(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return this.newSport || id == null;
    }


    public Sport setAsNew() {
        this.newSport = true;
        return this;
    }
}
