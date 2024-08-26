package com.adp.JobTraq.models;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "job_openings")
public class JobOpening {
    @Id
    private String id;
    private String title;
    private String state;
    private String city;
    private String country;
    private String companyName;
    private Double salary;
    private String description;
    private String status;
}
