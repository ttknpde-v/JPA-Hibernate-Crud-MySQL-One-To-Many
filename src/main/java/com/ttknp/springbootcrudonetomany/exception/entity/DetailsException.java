package com.ttknp.springbootcrudonetomany.exception.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsException {
    private Short status;
    private String currentCause;
    private LocalDate localDate;

}
