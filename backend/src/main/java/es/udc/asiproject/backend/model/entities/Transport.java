package es.udc.asiproject.backend.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transport {

    private Long id;
    private String name;

    public Transport() {
    }

    public Transport(String name) {
        super();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	    return id;
    }

    public void setId(Long id) {
	    this.id = id;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public String getName() {
	    return name;
    }

}
