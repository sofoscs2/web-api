package web.api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.dto.MatchOddsDto;
import web.api.dto.MatchOddsRequest;
import web.api.entity.MatchOdds;
import web.api.service.MatchOddsService;

import java.util.List;

@RestController
@RequestMapping("/api/match-odds")
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    public MatchOddsController(MatchOddsService matchOddsService) {
        this.matchOddsService = matchOddsService;
    }

    @GetMapping
    public List<MatchOddsDto> getAllMatchOdds() {
        return matchOddsService.getAllMatchOddsDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchOddsDto> getMatchOddsById(@PathVariable Long id) {
        MatchOddsDto matchOdds = matchOddsService.getMatchOddsDtoById(id);
        return matchOdds != null ? ResponseEntity.ok(matchOdds) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MatchOddsDto> createMatchOdds(@RequestBody MatchOddsRequest matchOddsRequest) {
        MatchOddsDto matchOdds = matchOddsService.createMatchOdds(matchOddsRequest);
        return matchOdds != null ? ResponseEntity.ok(matchOdds) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchOddsDto> updateMatchOdds(@PathVariable Long id, @RequestBody MatchOddsRequest matchOddsRequest) {
        MatchOddsDto matchOdds = matchOddsService.updateMatchOddsFromDto(id, matchOddsRequest);
        return matchOdds != null ? ResponseEntity.ok(matchOdds) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchOdds(@PathVariable Long id) {
        MatchOddsDto matchOdd = matchOddsService.getMatchOddsDtoById(id);
        matchOddsService.deleteMatchOdds(id);
        return matchOdd != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
