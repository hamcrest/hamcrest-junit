package org.hamcrest.junit;

import org.hamcrest.Matcher;
import org.hamcrest.junit.internal.Matching;
import org.hamcrest.junit.internal.MismatchAction;
import org.junit.AssumptionViolatedException;

/**
 * A set of methods useful for stating assumptions about the conditions in which a test is meaningful.
 * A failed assumption does not mean the code is broken, but that the test provides no useful information.
 * The default JUnit runner treats tests with failing assumptions as ignored.  Custom runners may behave differently.
 *
 * For example:
 * <pre>
 * // only provides information if database is reachable.
 * \@Test public void calculateTotalSalary() {
 *    DBConnection dbc = Database.connect();
 *    assumeNotNull(dbc);
 *    // ...
 * }
 * </pre>
 * These methods can be used directly: <code>Assume.assumeTrue(...)</code>, however, they
 * read better if they are referenced through static import:
 * <pre>
 * import static org.junit.Assume.*;
 *    ...
 *    assumeTrue(...);
 * </pre>
 *
 * @since 4.4
 */
public class MatcherAssume {
    /**
     * Call to assume that <code>actual</code> satisfies the condition specified by <code>matcher</code>.
     * If not, the test halts and is ignored.
     * Example:
     * <pre>:
     *   assumeThat(1, equalTo(1)); // passes
     *   foo(); // will execute
     *   assumeThat(0, equalTo(1)); // assumption failure! test halts
     *   int x = 1 / 0; // will never execute
     * </pre>
     *
     * @param <T> the static type accepted by the matcher (this can flag obvious compile-time problems such as <code>assumeThat(1, is("a")</code>)}
     * @param actual the computed value being compared
     * @param matcher an expression, built of {@link Matcher}s, specifying allowed values
     * @see org.hamcrest.Matcher
     * @see JUnitMatchers
     */
    public static <T> void assumeThat(T actual, Matcher<? super T> matcher) {
        assumeThat("", actual, matcher);
    }

    /**
     * Call to assume that <code>actual</code> satisfies the condition specified by <code>matcher</code>.
     * If not, the test halts and is ignored.
     * Example:
     * <pre>:
     *   assumeThat(1, is(1)); // passes
     *   foo(); // will execute
     *   assumeThat(0, is(1)); // assumption failure! test halts
     *   int x = 1 / 0; // will never execute
     * </pre>
     *
     * @param <T> the static type accepted by the matcher (this can flag obvious compile-time problems such as {@code assumeThat(1, is("a"))}
     * @param actual the computed value being compared
     * @param matcher an expression, built of {@link Matcher}s, specifying allowed values
     * @see org.hamcrest.CoreMatchers
     * @see JUnitMatchers
     */
    public static <T> void assumeThat(String message, T actual, Matcher<? super T> matcher) {
        Matching.checkMatch(message, actual, matcher, new MismatchAction() {
            @Override
            public void mismatch(String description) {
                throw new AssumptionViolatedException(description);
            }
        });
    }
}
