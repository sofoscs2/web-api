package web.api.service;


import org.springframework.stereotype.Service;
import web.api.dto.MatchOddsDto;
import web.api.dto.MatchOddsRequest;
import web.api.entity.Match;
import web.api.entity.MatchOdds;
import web.api.repository.MatchOddsRepository;
import web.api.repository.MatchRepository;
import web.api.utils.MatchOddsMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;

    private final MatchRepository matchRepository;

    private final MatchOddsMapper matchOddsMapper;

    public MatchOddsService(MatchOddsRepository matchOddsRepository, MatchRepository matchRepository, MatchOddsMapper matchOddsMapper) {
        this.matchOddsRepository = matchOddsRepository;
        this.matchRepository = matchRepository;
        this.matchOddsMapper = matchOddsMapper;
    }

    public List<MatchOdds> getAllMatchOdds() {
        return matchOddsRepository.findAll();
    }

    public MatchOdds getMatchOddsById(Long id) {
        return matchOddsRepository.findById(id).orElse(null);
    }

    public MatchOdds createMatchOdds(MatchOdds matchOdds) {
        return matchOddsRepository.save(matchOdds);
    }

    public MatchOdds updateMatchOdds(Long id, MatchOdds updatedMatchOdds) {
        return matchOddsRepository.findById(id)
                .map(odds -> {
                    odds.setSpecifier(updatedMatchOdds.getSpecifier());
                    odds.setOdd(updatedMatchOdds.getOdd());
                    return matchOddsRepository.save(odds);
                }).orElse(null);
    }

    public void deleteMatchOdds(Long id) {
        matchOddsRepository.deleteById(id);
    }

    public List<MatchOddsDto> getAllMatchOddsDto() {
        return matchOddsRepository.findAll().stream()
                .map(matchOddsMapper::toDto) // Use mapper for each entity
                .collect(Collectors.toList());
    }

    public MatchOddsDto getMatchOddsDtoById(Long id) {
        Optional<MatchOdds> matchOdds = matchOddsRepository.findById(id);
        return matchOdds.map(matchOddsMapper::toDto).orElse(null);
    }

    public MatchOddsDto createMatchOddsFromDto(MatchOddsDto matchOddsDto) {
        MatchOdds matchOdds = matchOddsRepository.save(matchOddsMapper.toEntity(matchOddsDto));
        return matchOddsMapper.toDto(matchOdds);
    }

    public MatchOddsDto createMatchOdds(MatchOddsRequest matchOddsRequest) {
        Optional<Match> match = matchRepository.findById(matchOddsRequest.getMatchId());
        if (match.isEmpty()) return null;
        MatchOdds matchOdds = new MatchOdds();
        matchOdds.setMatch(match.get());
        matchOdds.setOdd(matchOddsRequest.getOdd());
        matchOdds.setSpecifier(matchOddsRequest.getSpecifier());
        MatchOdds matchOddsResponse = matchOddsRepository.save(matchOdds);
        return matchOddsMapper.toDto(matchOddsResponse);
    }

    public MatchOddsDto updateMatchOddsFromDto(Long id, MatchOddsRequest matchOddsRequest) {
        Optional<Match> match = matchRepository.findById(matchOddsRequest.getMatchId());
        if (match.isEmpty()) return null;
        Optional<MatchOdds> matchOdds = matchOddsRepository.findById(id)
                .map(odds -> {
                    odds.setSpecifier(matchOddsRequest.getSpecifier());
                    odds.setOdd(matchOddsRequest.getOdd());
                    odds.setMatch(match.get());
                    return matchOddsRepository.save(odds);
                });
        return matchOdds.map(matchOddsMapper::toDto).orElse(null);
    }
}
