package web.api.utils;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import web.api.dto.MatchOddsDto;
import web.api.dto.MatchOddsRequest;
import web.api.entity.MatchOdds;
import web.api.repository.MatchRepository;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {

    @Mapping(target = "match.sport", expression = "java(SportDataEnum.getNameById(match.getSport()))")
    MatchOddsDto toDto(MatchOdds match);

    @Mapping(target = "match.sport", expression = "java(SportDataEnum.getIdByName(matchDto.getSport()))")
    MatchOdds toEntity(MatchOddsDto matchDto);

    @Mapping(ignore = true, target = "id")
    @Mapping(target = "match", expression = "java(matchRepository.findById(matchOddsRequest.getMatchId()).get())")
    MatchOdds reqToEntity(MatchOddsRequest matchOddsRequest, @Context MatchRepository matchRepository);
}