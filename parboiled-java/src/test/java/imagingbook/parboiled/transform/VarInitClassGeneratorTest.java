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

package imagingbook.parboiled.transform;

import imagingbook.parboiled.Rule;
import imagingbook.parboiled.common.ImmutableList;
import imagingbook.parboiled.parser.BaseParser;
import imagingbook.parboiled.support.Var;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VarInitClassGeneratorTest extends TransformationTest {

    private final List<RuleMethodProcessor> processors = ImmutableList.of(
            new UnusedLabelsRemover(),
            new ReturnInstructionUnifier(),
            new InstructionGraphCreator(),
            new ImplicitActionsConverter(),
            new InstructionGroupCreator(),
            new InstructionGroupPreparer(),
            new ActionClassGenerator(true),
            new VarInitClassGenerator(true)
    );

    static class Parser extends BaseParser<Integer> {

        @SuppressWarnings({"UnusedDeclaration"})
        public Rule A() {
            Var<List<String>> list = new Var<List<String>>(new ArrayList<String>());
            Var<Integer> i = new Var<Integer>(26);
            return Sequence('a', list.get().add(match()));
        }

    }

    @Before
    public void setup() throws IOException {
        setup(Parser.class);
    }

    @Test
    public void testVarInitClassGeneration() throws Exception {
        RuleMethod method = processMethod("A", processors);

        assertEquals(method.getGroups().size(), 3);

        InstructionGroup group = method.getGroups().get(0);
        Assert.assertEquals(AsmTestUtils.getClassDump(group.getGroupClassCode()), "" +
                "// class version 51.0 (51)\n" +
                "// access flags 0x1011\n" +
                "public final synthetic class imagingbook/parboiled/transform/VarInit$eYqwbz6zYKb27FsS extends imagingbook/parboiled/transform/BaseVarInit {\n" +
                "\n" +
                "\n" +
                "  // access flags 0x1\n" +
                "  public <init>(Ljava/lang/String;)V\n" +
                "    ALOAD 0\n" +
                "    ALOAD 1\n" +
                "    INVOKESPECIAL imagingbook/parboiled/transform/BaseVarInit.<init> (Ljava/lang/String;)V\n" +
                "    RETURN\n" +
                "    MAXSTACK = 2\n" +
                "    MAXLOCALS = 2\n" +
                "\n" +
                "  // access flags 0x1\n" +
                "  public create()Ljava/lang/Object;\n" +
                "    NEW java/util/ArrayList\n" +
                "    DUP\n" +
                "    INVOKESPECIAL java/util/ArrayList.<init> ()V\n" +
                "    ARETURN\n" +
                "    MAXSTACK = 2\n" +
                "    MAXLOCALS = 1\n" +
                "}\n");

        group = method.getGroups().get(1);
        Assert.assertEquals(AsmTestUtils.getClassDump(group.getGroupClassCode()), "" +
                "// class version 51.0 (51)\n" +
                "// access flags 0x1011\n" +
                "public final synthetic class imagingbook/parboiled/transform/VarInit$L7SMqNxExCwCkL9F extends imagingbook/parboiled/transform/BaseVarInit {\n" +
                "\n" +
                "\n" +
                "  // access flags 0x1\n" +
                "  public <init>(Ljava/lang/String;)V\n" +
                "    ALOAD 0\n" +
                "    ALOAD 1\n" +
                "    INVOKESPECIAL imagingbook/parboiled/transform/BaseVarInit.<init> (Ljava/lang/String;)V\n" +
                "    RETURN\n" +
                "    MAXSTACK = 2\n" +
                "    MAXLOCALS = 2\n" +
                "\n" +
                "  // access flags 0x1\n" +
                "  public create()Ljava/lang/Object;\n" +
                "    BIPUSH 26\n" +
                "    INVOKESTATIC java/lang/Integer.valueOf (I)Ljava/lang/Integer;\n" +
                "    ARETURN\n" +
                "    MAXSTACK = 1\n" +
                "    MAXLOCALS = 1\n" +
                "}\n");

        group = method.getGroups().get(2);
        Assert.assertEquals(AsmTestUtils.getClassDump(group.getGroupClassCode()), "" +
                "// class version 51.0 (51)\n" +
                "// access flags 0x1011\n" +
                "public final synthetic class imagingbook/parboiled/transform/Action$CXnu9x5PCAdIq4vP extends imagingbook/parboiled/transform/BaseAction {\n" +
                "\n" +
                "\n" +
                "  // access flags 0x1001\n" +
                "  public synthetic Limagingbook/parboiled/support/Var; field$0\n" +
                "\n" +
                "  // access flags 0x1001\n" +
                "  public synthetic Limagingbook/parboiled/transform/VarInitClassGeneratorTest$Parser; field$1\n" +
                "\n" +
                "  // access flags 0x1\n" +
                "  public <init>(Ljava/lang/String;)V\n" +
                "    ALOAD 0\n" +
                "    ALOAD 1\n" +
                "    INVOKESPECIAL imagingbook/parboiled/transform/BaseAction.<init> (Ljava/lang/String;)V\n" +
                "    RETURN\n" +
                "    MAXSTACK = 2\n" +
                "    MAXLOCALS = 2\n" +
                "\n" +
                "  // access flags 0x1\n" +
                "  public run(Limagingbook/parboiled/Context;)Z\n" +
                "    ALOAD 0\n" +
                "    GETFIELD imagingbook/parboiled/transform/Action$CXnu9x5PCAdIq4vP.field$0 : Limagingbook/parboiled/support/Var;\n" +
                "    INVOKEVIRTUAL imagingbook/parboiled/support/Var.get ()Ljava/lang/Object;\n" +
                "    CHECKCAST java/util/List\n" +
                "    ALOAD 0\n" +
                "    GETFIELD imagingbook/parboiled/transform/Action$CXnu9x5PCAdIq4vP.field$1 : Limagingbook/parboiled/transform/VarInitClassGeneratorTest$Parser;\n" +
                "    DUP\n" +
                "    ALOAD 1\n" +
                "    INVOKEINTERFACE imagingbook/parboiled/ContextAware.setContext (Limagingbook/parboiled/Context;)V (itf)\n" +
                "    INVOKEVIRTUAL imagingbook/parboiled/transform/VarInitClassGeneratorTest$Parser.match ()Ljava/lang/String;\n" +
                "    INVOKEINTERFACE java/util/List.add (Ljava/lang/Object;)Z (itf)\n" +
                "    IRETURN\n" +
                "    MAXSTACK = 4\n" +
                "    MAXLOCALS = 2\n" +
                "}\n");
    }

}
