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

package imagingbook.parboiled.examples.abc;

import imagingbook.parboiled.Rule;
import imagingbook.parboiled.annotations.BuildParseTree;
import imagingbook.parboiled.parser.BaseParser;

/**
 * A parser for the classic non-context free language example {@literal { a^n b^n c^n : n >= 1 }}
 * <br>
 * {@literal S <- &(A c) a+ B !(a|b|c)}
 * <br>
 * {@literal A <- a A? b}
 * <br>
 * {@literal B <- b B? c}
 */
@SuppressWarnings({"InfiniteRecursion"})
@BuildParseTree
public class AbcParser extends BaseParser<Object> {

    public Rule S() {
        return Sequence(
                Test(A(), 'c'),
                OneOrMore('a'),
                B(),
                TestNot(AnyOf("abc"))
        );
    }

    public Rule A() {
        return Sequence('a', Optional(A()), 'b');
    }

    public Rule B() {
        return Sequence('b', Optional(B()), 'c');
    }

}
