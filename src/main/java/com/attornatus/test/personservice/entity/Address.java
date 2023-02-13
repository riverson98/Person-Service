package com.attornatus.test.personservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "enderecos")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person pId;

    @Column(name = "cep", nullable = false, length = 20)
    private String zip;
    @Column(name = "end_principal", nullable = false)
    private Boolean mainAddress;

    @Column(name = "logradouro", nullable = false)
    private String nameOfTheStreet;

    @Column(name = "numero", nullable = false)
    private String number;

    @Column(name = "cidade", nullable = false)
    private String city;



    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Person getpId() {
        return pId;
    }

    public void setpId(Person pId) {
        this.pId = pId;
    }

    public String getNameOfTheStreet() {
        return nameOfTheStreet;
    }

    public void setNameOfTheStreet(String nameOfTheStreet) {
        this.nameOfTheStreet = nameOfTheStreet;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Boolean mainAddress) {
        this.mainAddress = mainAddress;
    }
}
