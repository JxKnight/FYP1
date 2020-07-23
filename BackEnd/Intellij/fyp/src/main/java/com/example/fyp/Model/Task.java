package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "task_id",unique = true)
    private String taskId;
    @Column(name = "task_description")
    private String taskDescription;
    @Column(name = "task_date")
    private String taskDate;
    @Column(name = "user_role")
    private String userRole;
}
