package web.api.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import web.api.dto.MatchDto;
import web.api.entity.Match;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mapping(target = "sport", expression = "java(SportDataEnum.getNameById(match.getSport()))")
    MatchDto toDto(Match match);

    // Optionally map DTO back to Entity (if needed)
    @Mapping(target = "sport", expression = "java(SportDataEnum.getIdByName(matchDto.getSport()))")
    Match toEntity(MatchDto matchDto);
}