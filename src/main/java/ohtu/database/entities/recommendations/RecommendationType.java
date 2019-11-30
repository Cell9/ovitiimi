package ohtu.database.entities.recommendations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

public enum RecommendationType {

    BOOK("Kirja"),
    LINKKI("Nettilähde"),
    PODCAST("Podcast"),
    VIDEO("Video");

    private static final List<RecommendationType> VALUES = Collections.unmodifiableList(Arrays.asList(RecommendationType.values()));

    @Getter
    private final String name;

    private RecommendationType(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return super.name();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static List<RecommendationType> valuesAsList() {
        return RecommendationType.VALUES;
    }

    public static RecommendationType valueOf(int ordinal) {
        return RecommendationType.VALUES.get(ordinal);
    }
}
