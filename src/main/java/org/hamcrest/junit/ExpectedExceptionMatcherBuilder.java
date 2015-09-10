package org.hamcrest.junit;

import static org.hamcrest.CoreMatchers.allOf;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

/**
 * Builds special matcher used by {@link ExpectedException}.
 */
class ExpectedExceptionMatcherBuilder {

    private final List<Matcher<? super Throwable>> matchers = new ArrayList<>();

    void add(Matcher<? super Throwable> matcher) {
        matchers.add(matcher);
    }

    boolean expectsThrowable() {
        return !matchers.isEmpty();
    }

    Matcher<? super Throwable> build() {
        return allOf(matchers);
    }

}
