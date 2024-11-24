package web.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchOddsDto {
    private Long id;
    private MatchDto match;
    private String specifier;
    private Double odd;
}
