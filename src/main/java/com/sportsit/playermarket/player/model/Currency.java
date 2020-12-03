package com.sportsit.playermarket.player.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(schema = "public", name = "currency")
public class Currency {

    @Id
    @Column(name = "code", insertable = true, updatable = false, nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

}
