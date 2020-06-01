package model.report;

public interface Reporter {
    void report(final String json);

    void report(final Report report);
}
