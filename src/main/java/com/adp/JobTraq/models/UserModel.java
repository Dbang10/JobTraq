package com.adp.JobTraq.models;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "job_portal.users")
public class UserModel {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private List<String> appliedJobIds = new ArrayList<>();

}
