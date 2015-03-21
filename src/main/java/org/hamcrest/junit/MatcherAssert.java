/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.junit;


import org.hamcrest.Matcher;
import org.hamcrest.junit.internal.Matching;
import org.hamcrest.junit.internal.MismatchAction;

public class MatcherAssert {
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        assertThat("", actual, matcher);
    }
    
    public static <T> void assertThat(String message, T actual, Matcher<? super T> matcher) {
        Matching.checkMatch(message, actual, matcher, new MismatchAction() {
            @Override
            public void mismatch(String description) {
                throw new AssertionError(description);
            }
        });
    }
}
