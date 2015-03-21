package org.hamcrest.junit.internal;

import org.hamcrest.*;

public class ThrowableMessageMatcher extends FeatureMatcher<Throwable,String> {
    public ThrowableMessageMatcher(Matcher<? super String> subMatcher) {
        super(subMatcher, "exception with message", "message");
    }


    @Override
    protected String featureValueOf(Throwable actual) {
        return actual.getMessage();
    }

    public static Matcher<Throwable> hasMessage(final Matcher<? super String> matcher) {
        return new ThrowableMessageMatcher(matcher);
    }
}
