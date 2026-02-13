package com.kollab.team;

import com.kollab.authentication.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"owner", "members"})
public class Team {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String label;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members = new HashSet<>();

//    @OneToMany(
//            mappedBy = "team",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<Project> projects = new HashSet<>();
}
