package org.hamcrest.junit.internal;

import org.hamcrest.*;

public class ThrowableCauseMatcher extends FeatureMatcher<Throwable, Throwable> {
    public ThrowableCauseMatcher(Matcher<? super Throwable> causeMatcher) {
        super(causeMatcher, "exception with cause", "cause");
    }

    @Override
    protected Throwable featureValueOf(Throwable actual) {
        return actual.getCause();
    }


    public static Matcher<Throwable> hasCause(final Matcher<? super Throwable> matcher) {
        return new ThrowableCauseMatcher(matcher);
    }
}
