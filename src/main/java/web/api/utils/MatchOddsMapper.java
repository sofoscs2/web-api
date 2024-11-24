package web.api.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import web.api.dto.MatchDto;
import web.api.dto.MatchOddsDto;
import web.api.entity.Match;
import web.api.entity.MatchOdds;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {

    @Mapping(target = "match.sport", expression = "java(SportDataEnum.getNameById(match.getSport()))")
    MatchOddsDto toDto(MatchOdds match);

    // Optionally map DTO back to Entity (if needed)
    @Mapping(target = "match.sport", expression = "java(SportDataEnum.getIdByName(matchDto.getSport()))")
    MatchOdds toEntity(MatchOddsDto matchDto);
}