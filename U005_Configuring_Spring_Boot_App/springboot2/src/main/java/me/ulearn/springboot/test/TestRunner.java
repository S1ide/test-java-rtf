package me.ulearn.springboot.test;

import me.ulearn.springboot.test.source.Film;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherConfig;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestRunner {
    public static TestResult run() {
        final Launcher launcher = buildLauncher();
        final SummaryGeneratingListener listener = new SummaryGeneratingListener();
        final List<? extends DiscoverySelector> selectors = List.of(
                DiscoverySelectors.selectClass(FilmTest.class)
        );

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(buildRequest(selectors));
        TestExecutionSummary summary = listener.getSummary();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        summary.printFailuresTo(new PrintWriter(outputStream, true, StandardCharsets.UTF_8));

        for (TestExecutionSummary.Failure failure : summary.getFailures()) {
            if (!(failure.getException() instanceof AssertionError)) {
                return new TestResult(TestVerdict.RuntimeError, outputStream.toString());
            }
        }

        return new TestResult(
                summary.getFailures().isEmpty() ? TestVerdict.Ok : TestVerdict.WrongAnswer,
                outputStream.toString()
        );
    }

    private static Launcher buildLauncher() {
        return LauncherFactory.create(LauncherConfig.DEFAULT);
    }

    private static LauncherDiscoveryRequest buildRequest(List<? extends DiscoverySelector> selectors) {
        return LauncherDiscoveryRequestBuilder.request()
                .selectors(selectors)
                .build();
    }
}
