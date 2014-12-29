package org.hamcrest.junit.internal;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.hamcrest.junit.internal.StacktracePrintingMatcher.isException;
import static org.hamcrest.junit.internal.StacktracePrintingMatcher.isThrowable;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StacktracePrintingMatcherTest {

    @Test
    public void succeedsWhenInnerMatcherSucceeds() throws Exception {
        assertTrue(isThrowable(any(Throwable.class)).matches(new Exception()));
    }

    @Test
    public void failsWhenInnerMatcherFails() throws Exception {
        assertFalse(isException(notNullValue(Exception.class)).matches(null));
    }

    @Test
    public void assertThatIncludesStacktrace() {
        Exception actual = new IllegalArgumentException("my message");
        Exception expected = new NullPointerException();

        try {
            assertThat(actual, isThrowable(equalTo(expected)));
        } catch (AssertionError e) {
            assertThat(e.getMessage(), containsString("Stacktrace was: java.lang.IllegalArgumentException: my message"));
        }
    }
}
