package com.myweb.storeapplication.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "roleKey")
    private Integer roleKey;

    @Column(name = "roleName")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
