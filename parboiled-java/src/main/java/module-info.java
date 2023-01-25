module imagingbook.parboiled.java {
    requires org.objectweb.asm;
    requires org.objectweb.asm.tree;
    requires org.objectweb.asm.tree.analysis;
    requires org.objectweb.asm.util;
    requires imagingbook.parboiled.core;

    exports org.parboiled.annotations;
    exports org.parboiled.testing;
    exports org.parboiled.parseXX;
}