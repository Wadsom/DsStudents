package com.ead.authuser.models;

import com.ead.authuser.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_ROLES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class RoleModel implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 30, unique = true, name = "roleName")
    @Enumerated(EnumType.STRING)
    private RoleType roleName;
    @ManyToMany(mappedBy = "roles")
    private Set<UserModel> users = new HashSet<>();

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.roleName.name();
    }
}
