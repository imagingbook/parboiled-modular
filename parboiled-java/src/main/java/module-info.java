/**
 * This is a modularized version of the <strong>parboiled</strong> PEG parser Java/Scala implementation originally
 * developed by Mathias Doenitz, refactored from
 * <a href="https://github.com/sirthias/parboiled">https://github.com/sirthias/parboiled</a>
 * (<a href= https://github.com/sirthias/parboiled/tree/6c9cded0ea240e572903234384d759562669877c>Version 1.4.1</a>,
 * Jan. 2023).
 */
module imagingbook.parboiled.java {
    requires org.objectweb.asm;
    requires org.objectweb.asm.tree;
    requires org.objectweb.asm.tree.analysis;
    requires org.objectweb.asm.util;
    requires imagingbook.parboiled.core;

    exports imagingbook.parboiled.annotations;
    exports imagingbook.parboiled.testing;
    exports imagingbook.parboiled.parser;
}