package com.adp.JobTraq.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "applications")
public class JobApplication {

    @Id
    private String id; // MongoDB's _id field

    @Field(name = "job_id")
    private String jobId;

    @Field(name = "user_id")
    private String userId;

    @Field(name = "applicant_state")
    private String applicantState;

    @Field(name = "applicant_city")
    private String applicantCity;

    @Field(name = "highest_education")
    private String highestEducation;

    @Field(name = "college")
    private String college;

    @Field(name = "field_of_study")
    private String fieldOfStudy;

    @Field(name = "start_date")
    private Date startDate;

    @Field(name = "end_date")
    private Date endDate;

    @Field(name = "gpa")
    private Double gpa;

    @Field(name = "job_title")
    private List<String> jobTitle;

    @Field(name = "company")
    private List<String> company;

    @Field(name = "job_start_date")
    private List<Date> jobStartDate;

    @Field(name = "location")
    private List<String> location;

    @Field(name = "job_description")
    private List<String> jobDescription;

    @Field(name = "job_end_date")
    private List<Date> jobEndDate;

    @Field(name = "job_accepted")
    private boolean jobAccepted;

    @Field(name = "job_denied")
    private boolean jobDenied;

    @Field(name = "job_offered")
    private boolean jobOffered;
}
