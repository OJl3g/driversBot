package org.ojl3g.driversbot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "forwards")
@NoArgsConstructor
public class ForwardingDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private long chatId;
    private String fullName;


}
