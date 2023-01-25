/**
 * This is a modularized version of the <strong>parboiled</strong> PEG parser Java/Scala implementation originally
 * developed by Mathias Doenitz, refactored from
 * <a href="https://github.com/sirthias/parboiled">https://github.com/sirthias/parboiled</a>
 * (<a href= https://github.com/sirthias/parboiled/tree/6c9cded0ea240e572903234384d759562669877c>Version 1.4.1</a>,
 * Jan. 2023).
 */
module imagingbook.parboiled.examples.java {
    requires imagingbook.parboiled.core;
    requires imagingbook.parboiled.java;

    exports imagingbook.parboiled.examples.abc;
    exports imagingbook.parboiled.examples.calculators;
    exports imagingbook.parboiled.examples.indenting;
    exports imagingbook.parboiled.examples.java;
    exports imagingbook.parboiled.examples.sparql;
    exports imagingbook.parboiled.examples.time;
}