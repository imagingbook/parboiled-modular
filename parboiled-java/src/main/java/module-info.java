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