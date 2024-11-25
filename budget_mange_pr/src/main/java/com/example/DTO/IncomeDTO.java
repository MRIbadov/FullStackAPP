package com.example.DTO;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class IncomeDTO {
    private String source;
    private Double amount;
    private LocalDate dateReceived;
    private Long userId;
}
