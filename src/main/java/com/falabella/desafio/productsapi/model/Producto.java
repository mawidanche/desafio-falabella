package com.falabella.desafio.productsapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "PRODUCTO")
public class Producto  implements Serializable{
 
    private static final long serialVersionUID = 8282435803653095320L;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
    @Column(insertable = false, updatable = false, nullable = false)
    @Id
    @Getter @Setter
    private String uuid;

    @Getter @Setter
    private String sku;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String brand;
    @Getter @Setter
    private String size;
    @Getter @Setter
    private BigDecimal price;

    @Getter @Setter
    @JoinColumn(name = "principal_image", referencedColumnName = "uuid")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Imagen principalImage;
    
    @Getter @Setter
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Imagen> otherImage;
}
