/**
 * This is a modularized version of the <strong>parboiled</strong> PEG parser Java/Scala implementation originally
 * developed by Mathias Doenitz, refactored from
 * <a href="https://github.com/sirthias/parboiled">https://github.com/sirthias/parboiled</a>
 * (<a href= https://github.com/sirthias/parboiled/tree/6c9cded0ea240e572903234384d759562669877c>Version 1.4.1</a>,
 * Jan. 2023).
 */
module org.parboiled.core {
    exports org.parboiled.buffers;
    exports org.parboiled.common;
    exports org.parboiled.errors;
    exports org.parboiled.matchers;
    exports org.parboiled.parserunners;
    exports org.parboiled.support;
    exports org.parboiled.trees;
    exports org.parboiled;
}
