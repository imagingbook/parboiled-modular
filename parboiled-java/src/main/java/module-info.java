module imagingbook.parboiled.java {
    requires imagingbook.parboiled.core;
    requires org.objectweb.asm;
    requires org.objectweb.asm.tree;
    requires org.objectweb.asm.tree.analysis;
    requires org.objectweb.asm.util;

    exports org.parboiled;
    exports org.parboiled.annotations;
}