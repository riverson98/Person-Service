package com.attornatus.test.personservice.dto;


import com.attornatus.test.personservice.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonDto {

    @JsonProperty("idPessoa")
    private Integer personId;

    @JsonProperty("nome")
    @NotBlank
    private String name;

    @JsonProperty("datNascimento")
    @NotBlank
    private String birth;

    @JsonProperty("enderecos")
    private List<Address> address;

}
