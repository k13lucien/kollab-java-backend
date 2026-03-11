package com.kollab.task;

import com.kollab.authentication.model.User;
import com.kollab.project.Project;
import com.kollab.task.enums.TaskPriority;
import com.kollab.task.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String label;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(
            name = "project_id",
            nullable = false
    )
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
