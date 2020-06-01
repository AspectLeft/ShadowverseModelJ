package model.report;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Report {
    public enum ReportType {
        @SerializedName("0")
        CARD_INTO_HAND,

        @SerializedName("1")
        ACTIVATED_EFFECT,

        @SerializedName("2")
        TAKE_DAMAGE,

        @SerializedName("3")
        HEAL,

        @SerializedName("4")
        CARD_DESTROYED,

        @SerializedName("5")
        BUFFED,

        @SerializedName("6")
        CHANGE_CARD_ON_START,

        @SerializedName("7")
        DISCARD_CARD,

        @SerializedName("8")
        INCREASE_SHADOW,

        @SerializedName("9")
        AREA_OVERFLOW,

        @SerializedName("10")
        CARD_INTO_PLAY,

        @SerializedName("11")
        SPELL_BOOST,

        @SerializedName("12")
        RAMP,

        @SerializedName("13")
        DECREASE_SHADOW,

        @SerializedName("14")
        CARD_BANISHED,

        @SerializedName("15")
        FOLLOWER_EVOLVED,

        @SerializedName("16")
        CARDS_INTO_DECK,

        @SerializedName("17")
        CARD_COST_CHANGED
    }

    @SerializedName("type")
    @Getter
    @Setter
    private ReportType type;

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int[] values;
}
