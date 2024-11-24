package web.api.repository;


import web.api.entity.Match;
import web.api.entity.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {
    MatchOdds findByMatchAndSpecifier(Match match, String specifier);
}
