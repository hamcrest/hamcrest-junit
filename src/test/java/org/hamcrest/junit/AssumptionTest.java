package org.hamcrest.junit;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.junit.MatcherAssume.assumeThat;
import static org.junit.Assert.assertTrue;

public class AssumptionTest {
    @Test
    public void failingAssumeThrowsPublicAssumptionViolatedException() {
        Matcher<Integer> assumption = equalTo(4);

        try {
            assumeThat(3, assumption);
        } catch (org.junit.AssumptionViolatedException e) {
            assertTrue(e.getMessage().contains(StringDescription.toString(assumption)));
        }
    }

    @Test
    public void passingAssumeDoesNothing() {
        assumeThat(3, equalTo(3));
    }


    @Test
    public void failingAssumeWithMessageReportsBothMessageAndMatcher() {
        String message = "Some random message string.";
        Matcher<Integer> assumption = equalTo(4);

        try {
            assumeThat(message, 3, assumption);
        } catch (org.junit.AssumptionViolatedException e) {
            assertTrue(e.getMessage().contains(message));
            assertTrue(e.getMessage().contains(StringDescription.toString(assumption)));
        }
    }
}
