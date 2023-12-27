package com.market.superMarket.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Boolean available;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "author-lenguajes", joinColumns = @JoinColumn(name = "authorId", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "lenguageId", referencedColumnName = "id")
    )
    private Set<Lenguage> lenguagesList;

    @OneToMany(mappedBy = "autor",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books;

    //constructor
    public Author() {
    }
    public Author(String name) {
        this.name = name;
    }
    public Author(String name, String email, String address, Boolean available) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.available = available;
    }


    //Getter and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Set<Lenguage> getLenguagesList() {
        return lenguagesList;
    }

    public void setLenguagesList(Set<Lenguage> lenguagesList) {
        this.lenguagesList = lenguagesList;
    }
}
