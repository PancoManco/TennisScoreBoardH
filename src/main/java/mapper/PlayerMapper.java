package mapper;

import dto.PlayerDto;
import model.Player;
import org.mapstruct.factory.Mappers;

public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDto toDTO(Player player);
    Player toEntity(PlayerDto playerDTO);
}
