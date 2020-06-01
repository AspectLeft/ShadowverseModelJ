package model.effect;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Choice {
    private ChoiceType type;
    private int value;
}
