package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String url;
    @Column(unique = true)
    private String shortCode;


    private Instant createdAt;
    private Instant updatedAt;



    private Long accessCount;
}
