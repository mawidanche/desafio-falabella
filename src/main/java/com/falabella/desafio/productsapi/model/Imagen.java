package com.falabella.desafio.productsapi.model;

 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "IMAGEN")
@JsonSerialize
@Builder
public class Imagen implements Serializable{
 
    private static final long serialVersionUID = 1726981649324216837L;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(insertable = false, updatable = false, nullable = false)
    @Id
    @Getter @Setter
    private String uuid;

    @Getter @Setter
    private String url;

    @ManyToOne(optional = true)
    @JoinColumn(name="producto", referencedColumnName = "uuid")
    @JsonIgnore
    @Getter @Setter
    private Producto producto;
 

}
