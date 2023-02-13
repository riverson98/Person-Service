package com.attornatus.test.personservice.dto;

import com.attornatus.test.personservice.entity.Address;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseDto {

    private Integer id;

    private String name;

    private String birth;

    private List<Address> address;

    private Integer idPerson;
}
