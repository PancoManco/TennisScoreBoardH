package mapper;

import dto.MatchDto;
import model.Match;
import org.mapstruct.factory.Mappers;

public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);
    MatchDto toDTO(Match match);
    Match toEntity(MatchDto matchDTO);
}
