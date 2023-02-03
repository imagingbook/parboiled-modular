/**
 * This is a modularized version of the <strong>parboiled</strong> PEG parser Java/Scala implementation originally
 * developed by Mathias Doenitz, refactored from
 * <a href="https://github.com/sirthias/parboiled">https://github.com/sirthias/parboiled</a>
 * (<a href= https://github.com/sirthias/parboiled/tree/6c9cded0ea240e572903234384d759562669877c>Version 1.4.1</a>,
 * Jan. 2023).
 */
module org.parboiled.examples.java {
    requires org.parboiled.core;
    requires org.parboiled.java;

    exports org.parboiled.examples.abc;
    exports org.parboiled.examples.calculators;
    exports org.parboiled.examples.indenting;
    exports org.parboiled.examples.java;
    exports org.parboiled.examples.sparql;
    exports org.parboiled.examples.time;
}