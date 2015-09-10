package org.hamcrest.junit;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.junit.JUnitMatchers.isThrowable;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

/**
 * Builds special matcher used by {@link ExpectedException}.
 */
class ExpectedExceptionMatcherBuilder {

    private final List<Matcher<? super Throwable>> matchers = new ArrayList<Matcher<? super Throwable>>();

    void add(Matcher<? super Throwable> matcher) {
        matchers.add(matcher);
    }

    boolean expectsThrowable() {
        return !matchers.isEmpty();
    }

    Matcher<Throwable> build() {
        return isThrowable(allOfTheMatchers());
    }

    private Matcher<Throwable> allOfTheMatchers() {
        if (matchers.size() == 1) {
            return cast(matchers.get(0));
        }
        return allOf(matchers);
    }

    @SuppressWarnings("unchecked")
    private Matcher<Throwable> cast(Matcher<?> singleMatcher) {
        return (Matcher<Throwable>) singleMatcher;
    }
}
