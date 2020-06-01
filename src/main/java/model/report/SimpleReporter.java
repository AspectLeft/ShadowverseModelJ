package model.report;

import com.google.gson.Gson;

public class SimpleReporter implements Reporter {
    private Gson gson = new Gson();

    @Override
    public void report(final String json) {
        System.out.println(json);
    }

    @Override
    public void report(final Report report) {
        report(gson.toJson(report));
    }
}
