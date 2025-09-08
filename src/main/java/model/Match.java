package model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matches", schema = "public")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Player player2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    Player winner;
    @Transient
    private MatchScore matchScore;

}
