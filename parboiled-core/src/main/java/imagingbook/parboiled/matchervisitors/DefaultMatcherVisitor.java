/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package imagingbook.parboiled.matchervisitors;

import imagingbook.parboiled.matchers.AbstractMatcher;
import imagingbook.parboiled.matchers.ActionMatcher;
import imagingbook.parboiled.matchers.AnyMatcher;
import imagingbook.parboiled.matchers.AnyOfMatcher;
import imagingbook.parboiled.matchers.CharIgnoreCaseMatcher;
import imagingbook.parboiled.matchers.CharMatcher;
import imagingbook.parboiled.matchers.CharRangeMatcher;
import imagingbook.parboiled.matchers.CustomMatcher;
import imagingbook.parboiled.matchers.EmptyMatcher;
import imagingbook.parboiled.matchers.FirstOfMatcher;
import imagingbook.parboiled.matchers.NothingMatcher;
import imagingbook.parboiled.matchers.OneOrMoreMatcher;
import imagingbook.parboiled.matchers.OptionalMatcher;
import imagingbook.parboiled.matchers.SequenceMatcher;
import imagingbook.parboiled.matchers.TestMatcher;
import imagingbook.parboiled.matchers.TestNotMatcher;
import imagingbook.parboiled.matchers.ZeroOrMoreMatcher;

/**
 * A basic {@link MatcherVisitor} implementation that delegates all visiting methods to one default value method.
 *
 * @param <R> the return value of this visitor
 */
public class DefaultMatcherVisitor<R> implements MatcherVisitor<R> {

    public R visit(ActionMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(AnyMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(CharIgnoreCaseMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(CharMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(CharRangeMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(AnyOfMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(CustomMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(EmptyMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(FirstOfMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(NothingMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(OneOrMoreMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(OptionalMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(SequenceMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(TestMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(TestNotMatcher matcher) {
        return defaultValue(matcher);
    }

    public R visit(ZeroOrMoreMatcher matcher) {
        return defaultValue(matcher);
    }

    /**
     * Returns the default value for all visiting methods that have not been overridden.
     *
     * @param matcher the matcher
     * @return the return value (null by default)
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public R defaultValue(AbstractMatcher matcher) {
        return null;
    }

}
