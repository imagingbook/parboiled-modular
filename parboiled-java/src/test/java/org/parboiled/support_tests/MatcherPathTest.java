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

package org.parboiled.support_tests;

import org.parboiled.Rule;
import org.parboiled.matchers.Matcher;
import org.parboiled.parser.BaseParser;
import org.parboiled.parser.Parboiled;
import org.junit.Assert;
import org.junit.Test;
import org.parboiled.support.MatcherPath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatcherPathTest {

    static class Parser extends BaseParser<Object> {
        public Rule A() {
            return toRule("a");
        }

        public Rule B() {
            return toRule("b");
        }

        public Rule C() {
            return toRule("c");
        }
    }

    @Test
    public void testMatcherPath() {
        Parser parser = Parboiled.createParser(Parser.class);
        MatcherPath path1 =
                new MatcherPath(new MatcherPath.Element((Matcher)parser.A(), 0, 2),
                new MatcherPath(new MatcherPath.Element((Matcher)parser.B(), 0, 1),
                new MatcherPath(new MatcherPath.Element((Matcher)parser.C(), 0, 0), null)));
        MatcherPath path2 = path1.parent;

        assertEquals(path1.toString(), "C/B/A");
        assertEquals(path2.toString(), "C/B");
        assertTrue(path2.isPrefixOf(path1));
        assertTrue(path1.contains((Matcher)parser.B()));
        Assert.assertEquals(path1.getElementAtLevel(0).matcher.toString(), "C");
    }

}
