/*
 * Copyright (C) 2013 Chris Leishman
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

package org.parboiled.matching;

import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.annotations.MemoMismatches;
import org.parboiled.parser.BaseParser;
import org.parboiled.parser.Parboiled;
import org.parboiled.testing.TestNgParboiledTest;
import org.junit.Test;
import org.parboiled.testing.TestNgParboiledTest;

public class MemoMismatchesMatcherTest extends TestNgParboiledTest<Object> {

    @BuildParseTree
    static class Parser extends BaseParser<Object> {

        public Rule Test1() {
            return FirstOf(FirstChoice(), "XYZ");
        }

        @MemoMismatches
        public Rule FirstChoice() {
            return FirstOf("ABC", "CBA");
        }
    }

    @Test
    public void testMatchesSubsequently() {
        Parser parser = Parboiled.createParser(Parser.class);
        test(parser.Test1(), "XYZ").hasNoErrors().hasParseTree("[Test1] 'XYZ'\n  [\"XYZ\"] 'XYZ'\n");
        test(parser.Test1(), "ABC").hasNoErrors().hasParseTree("[Test1] 'ABC'\n  [FirstChoice] 'ABC'\n");
    }
}
