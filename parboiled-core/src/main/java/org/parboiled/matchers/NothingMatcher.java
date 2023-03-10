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

package org.parboiled.matchers;

import org.parboiled.MatcherContext;
import org.parboiled.matchervisitors.MatcherVisitor;

import static org.parboiled.common.Preconditions.checkArgNotNull;

/**
 * A {@link Matcher} that never matches anything.
 */
public class NothingMatcher extends AbstractMatcher {

    public NothingMatcher() {
        super("NOTHING");
    }

    public boolean match(MatcherContext context) {
        return false;
    }

    public <R> R accept(MatcherVisitor<R> visitor) {
        checkArgNotNull(visitor, "visitor");
        return visitor.visit(this);
    }
}
