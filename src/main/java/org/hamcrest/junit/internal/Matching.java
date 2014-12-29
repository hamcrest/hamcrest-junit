/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.junit.internal;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class Matching {
    public static <T> void checkMatch(String reason, T actual, Matcher<? super T> matcher, MismatchAction action) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason)
                       .appendText("\nExpected: ")
                       .appendDescriptionOf(matcher)
                       .appendText("\n     but: ");
            matcher.describeMismatch(actual, description);
            
            action.mismatch(description.toString());
        }
    }
}
