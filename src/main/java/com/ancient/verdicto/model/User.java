package com.ancient.verdicto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Transient
    public static final String SEQUENCE_NAME="user_sequence";

    @Id
    private long userId;

    private String password;
    private String emailId;

}
