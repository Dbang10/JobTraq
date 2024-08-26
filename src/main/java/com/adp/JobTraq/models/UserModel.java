package com.adp.JobTraq.models;

// import jakarta.persistence.Id;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "job_portal.users")
public class UserModel {

    @Id
    private String id;
    private String name;
    @Indexed
    private String email;
    private String password;
    private boolean active;
    private String role;
    private List<String> appliedJobIds = new ArrayList<>();

}
