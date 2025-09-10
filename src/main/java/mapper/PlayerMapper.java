package mapper;

import dto.PlayerDto;
import model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    PlayerDto toDTO(Player player);
    Player toEntity(PlayerDto playerDTO);
}
