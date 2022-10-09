package ru.dawgg.bookmarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dawgg.bookmarket.model.characteristic.Role;
import ru.dawgg.bookmarket.model.characteristic.State;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "site_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email")
    private String email;
    @Column(name = "hash_password")
    private String hashPassword;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "locked")
    private boolean locked;
    @Column(name = "enabled")
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
}