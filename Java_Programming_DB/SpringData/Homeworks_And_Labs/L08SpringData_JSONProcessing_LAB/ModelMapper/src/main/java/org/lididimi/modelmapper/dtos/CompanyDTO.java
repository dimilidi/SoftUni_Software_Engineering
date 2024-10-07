package org.lididimi.modelmapper.dtos;

import com.google.gson.annotations.Expose;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyDTO {

    @Expose
    private String name;

    @Expose
    private List<CreateEmployeeDTO> employees;

}
