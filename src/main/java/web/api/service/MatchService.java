package web.api.service;


import org.springframework.stereotype.Service;
import web.api.dto.MatchDto;
import web.api.entity.Match;
import web.api.repository.MatchRepository;
import web.api.utils.MatchMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    private final MatchMapper matchMapper;

    public MatchService(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public List<MatchDto> getAllMatchesDto() {
        return matchRepository.findAll().stream()
                .map(matchMapper::toDto) // Use mapper for each entity
                .collect(Collectors.toList());
    }

    public MatchDto getMatchDtoById(Long id) {
        Optional<Match> match = matchRepository.findById(id);
        return match.map(matchMapper::toDto).orElse(null);
    }

    public MatchDto createMatchFromDto(MatchDto matchDto) {
        Match match = matchRepository.save(matchMapper.toEntity(matchDto));
        return matchMapper.toDto(match);
    }

    public MatchDto updateMatchFromDto(Long id, MatchDto updatedMatchDto) {
        Match updatedMatch = matchMapper.toEntity(updatedMatchDto);
        Optional<Match> matchResp =  matchRepository.findById(id)
                .map(match -> {
                    match.setDescription(updatedMatch.getDescription());
                    match.setMatchDate(updatedMatch.getMatchDate());
                    match.setMatchTime(updatedMatch.getMatchTime());
                    match.setTeamA(updatedMatch.getTeamA());
                    match.setTeamB(updatedMatch.getTeamB());
                    match.setSport(updatedMatch.getSport());
                    return matchRepository.save(match);
                });
        return matchResp.map(matchMapper::toDto).orElse(null);
    }
}
