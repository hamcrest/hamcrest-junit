/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.junit;


import org.hamcrest.Matcher;
import org.hamcrest.junit.internal.Matching;
import org.hamcrest.junit.internal.MismatchAction;

import static org.hamcrest.Matchers.allOf;

public class MatcherAssert {

    /**
     * Assert that {@code actual} satisfies <i>all of</i> the given matchers.
     *
     * @throws IllegalArgumentException if no matchers were specified
     *
     * @see org.hamcrest.Matchers#allOf(Matcher[])
     */
    public static <T> void assertThat(T actual, Matcher<? super T>... matchers) {
        assertThat("", actual, matchers);
    }

    /**
     * Assert that {@code actual} satisfies <i>all of</i> the given matchers.
     *
     * @throws IllegalArgumentException if no matchers were specified
     *
     * @see org.hamcrest.Matchers#allOf(Matcher[])
     */
    public static <T> void assertThat(String message, T actual, Matcher<? super T>... matchers) {
        if (matchers.length == 0) {
            throw new IllegalArgumentException("No matchers were specified");
        }
        assertThat(message, actual, allOf(matchers));
    }

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
