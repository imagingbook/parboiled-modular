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

package imagingbook.parboiled.parserunners;

import imagingbook.parboiled.MatchHandler;
import imagingbook.parboiled.MatcherContext;
import imagingbook.parboiled.Rule;
import imagingbook.parboiled.buffers.DefaultInputBuffer;
import imagingbook.parboiled.buffers.InputBuffer;
import imagingbook.parboiled.errors.ParseError;
import imagingbook.parboiled.matchers.Matcher;
import imagingbook.parboiled.support.DefaultValueStack;
import imagingbook.parboiled.support.ParsingResult;
import imagingbook.parboiled.support.ValueStack;

import java.util.ArrayList;
import java.util.List;

import static imagingbook.parboiled.common.Preconditions.checkArgNotNull;

public abstract class AbstractParseRunner<V> implements ParseRunner<V> {
    private final Matcher rootMatcher;
    private List<ParseError> parseErrors;
    private ValueStack<V> valueStack;
    private Object initialValueStackSnapshot;

    public AbstractParseRunner(Rule rule) {
        this.rootMatcher = checkArgNotNull((Matcher) rule, "rule");
    }

    public Matcher getRootMatcher() {
        return rootMatcher;
    }

    public ParseRunner<V> withParseErrors(List<ParseError> parseErrors) {
        this.parseErrors = parseErrors;
        return this;
    }

    public List<ParseError> getParseErrors() {
        if (parseErrors == null) {
            withParseErrors(new ArrayList<ParseError>());
        }
        return parseErrors;
    }

    public ParseRunner<V>withValueStack(ValueStack<V> valueStack) {
        this.valueStack = checkArgNotNull(valueStack, "valueStack");
        this.initialValueStackSnapshot = valueStack.takeSnapshot();
        return this;
    }

    public ValueStack<V> getValueStack() {
        if (valueStack == null) {
            withValueStack(new DefaultValueStack<V>());
        }
        return valueStack;
    }    

    public ParsingResult<V> run(String input) {
        checkArgNotNull(input, "input");
        return run(input.toCharArray());
    }

    public ParsingResult<V> run(char[] input) {
        checkArgNotNull(input, "input");
        return run(new DefaultInputBuffer(input));
    }

    protected void resetValueStack() {
        getValueStack().restoreSnapshot(initialValueStackSnapshot);
    }

    protected MatcherContext<V> createRootContext(InputBuffer inputBuffer, MatchHandler matchHandler,
                                                     boolean fastStringMatching) {
        return new MatcherContext<V>(inputBuffer, getValueStack(), getParseErrors(), matchHandler, rootMatcher,
                fastStringMatching);
    }
    
    protected ParsingResult<V> createParsingResult(boolean matched, MatcherContext<V> rootContext) {
        return new ParsingResult<V>(matched, rootContext.getNode(), getValueStack(), getParseErrors(),
                rootContext.getInputBuffer());
    }
}
