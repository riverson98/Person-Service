package com.attornatus.test.personservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddressDto {

    @JsonProperty("idEndereco")
    private Integer addressId;

    @JsonProperty("idPessoa")
    private Integer idPerson;

    @JsonProperty("cep")
    @NotBlank
    private String zip;

    @JsonProperty("endPrincipal")
    @NonNull
    private Boolean mainAddress;

    @JsonProperty("rua")
    @NotBlank
    private String nameOfTheStreet;

    @JsonProperty("numero")
    @NotBlank
    private String number;

    @JsonProperty("cidade")
    @NotBlank
    private String city;
}
