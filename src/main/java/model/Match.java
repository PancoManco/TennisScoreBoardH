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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id")
    private Player player1;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "player2_id")
    private Player player2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    Player winner;

    @Transient
    private MatchScore matchScore;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = new MatchScore(player1.getName(), player2.getName());
    }
}
