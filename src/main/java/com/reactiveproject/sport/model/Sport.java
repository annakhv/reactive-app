package com.reactiveproject.sport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Sport implements Persistable<Long> {
    @Id
    private final Long id;
    private final String name;
    @Transient
    @JsonIgnore
    private boolean newSport;

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
