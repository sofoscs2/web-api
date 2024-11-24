package web.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Getter
@Setter
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @Column (name = "match_date")
    private LocalDate matchDate;
    @Column (name = "match_time")
    private LocalTime matchTime;
    @Column (name = "team_a")
    private String teamA;
    @Column (name = "team_b")
    private String teamB;
    private Integer sport;
}
