package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.MatchScore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private int id;
    private PlayerDto player1;
    private PlayerDto player2;
    private PlayerDto winner;
    private MatchScore matchScore;
}