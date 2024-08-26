package com.adp.JobTraq.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("test-user")
public class AuthUser {
    @Id
    private String id;
    @Indexed
    private String email;
    private String password;
    private boolean active;
}
