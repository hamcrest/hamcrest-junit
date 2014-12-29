package org.hamcrest.junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link org.hamcrest.junit.MatcherAssert}
 */
public class AssertionTest {
    @Test
    public void assertThatIncludesDescriptionOfTestedValueInErrorMessage() {
        String expected = "expected";
        String actual = "actual";

        String expectedMessage = "identifier\nExpected: \"expected\"\n     but: was \"actual\"";

        try {
            assertThat("identifier", actual, equalTo(expected));
        } catch (AssertionError e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void assertThatIncludesAdvancedMismatch() {
        String expectedMessage = "identifier\nExpected: is an instance of java.lang.Integer\n     but: \"actual\" is a java.lang.String";

        try {
            assertThat("identifier", "actual", is(instanceOf(Integer.class)));
        } catch (AssertionError e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void assertThatDescriptionCanBeElided() {
        String expected = "expected";
        String actual = "actual";

        String expectedMessage = "\nExpected: \"expected\"\n     but: was \"actual\"";

        try {
            assertThat(actual, equalTo(expected));
        } catch (AssertionError e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }
}
