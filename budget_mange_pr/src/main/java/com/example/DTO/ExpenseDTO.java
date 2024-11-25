package com.example.DTO;

import lombok.Data;

import java.util.Date;

@Data

public class ExpenseDTO {
    private Long id;

    private String title;

    private String category;

    private Date local_date;

    private Integer amount;
    private Long userId;


}
