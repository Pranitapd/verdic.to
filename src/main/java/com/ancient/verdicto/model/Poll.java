package com.ancient.verdicto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Poll {
    @Id
    private long pollId;

    private String description;
    private LocalDate createDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate pollStartDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate pollEndDate;

    private boolean isActive;
    private String createdByUser;

    private List<String> options;
}
