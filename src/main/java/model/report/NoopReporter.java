package model.report;

public class NoopReporter implements Reporter {
    @Override
    public void report(String json) {
    }

    @Override
    public void report(Report report) {
    }
}
