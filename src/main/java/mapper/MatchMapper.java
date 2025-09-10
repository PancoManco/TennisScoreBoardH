package mapper;

import dto.MatchDto;
import model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);
    MatchDto toDTO(Match match);
    Match toEntity(MatchDto matchDTO);
}
