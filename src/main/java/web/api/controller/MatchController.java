package web.api.controller;


import web.api.dto.MatchDto;
import web.api.entity.Match;
import web.api.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.api.utils.SportDataEnum;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<MatchDto> getAllMatches() {
        return matchService.getAllMatchesDto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id) {
        MatchDto match = matchService.getMatchDtoById(id);
        return match != null ? ResponseEntity.ok(match) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public MatchDto createMatch(@RequestBody MatchDto match) {
        return matchService.createMatchFromDto(match);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Long id, @RequestBody MatchDto updatedMatch) {
        MatchDto match = matchService.updateMatchFromDto(id, updatedMatch);
        return match != null ? ResponseEntity.ok(match) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        MatchDto match = matchService.getMatchDtoById(id);
        matchService.deleteMatch(id);
        return match != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
