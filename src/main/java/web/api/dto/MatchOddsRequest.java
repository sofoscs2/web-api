package web.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchOddsRequest {
    private Long matchId;
    private String specifier;
    private Double odd;
}
