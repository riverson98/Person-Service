package com.attornatus.test.personservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "enderecos")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idEndereco")
    private Integer addressId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonProperty("idPessoa")
    private Person pId;

    @Column(name = "cep", nullable = false, length = 20)
    @JsonProperty("cep")
    @NotBlank
    private String zip;
    @Column(name = "end_principal", nullable = false)
    @JsonProperty("endPrincipal")
    @NotNull
    private Boolean mainAddress;

    @Column(name = "logradouro", nullable = false)
    @JsonProperty("rua")
    @NotBlank
    private String nameOfTheStreet;

    @Column(name = "numero", nullable = false)
    @JsonProperty("numero")
    @NotBlank
    private String number;

    @Column(name = "cidade", nullable = false)
    @JsonProperty("cidade")
    @NotBlank
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
