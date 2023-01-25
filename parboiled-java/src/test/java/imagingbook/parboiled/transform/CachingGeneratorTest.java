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

import imagingbook.parboiled.common.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CachingGeneratorTest extends TransformationTest {

    private final List<RuleMethodProcessor> processors = ImmutableList.of(
            new BodyWithSuperCallReplacer(),
            new LabellingGenerator(),
            new FlagMarkingGenerator(),
            new CachingGenerator()
    );

    @Before
    public void setup() throws IOException {
        setup(TestParser.class);
    }

    @SuppressWarnings( {"unchecked"})
    @Test
    public void testCachingGenerator() throws Exception {
        AsmTestUtils.assertTraceDumpEquality(processMethod("RuleWithoutAction", processors), "" +
                "    ALOAD 0\n" +
                "    GETFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithoutAction : Limagingbook/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L0\n" +
                "    ARETURN\n" +
                "   L0\n" +
                "    POP\n" +
                "    NEW imagingbook/parboiled/matchers/ProxyMatcher\n" +
                "    DUP\n" +
                "    INVOKESPECIAL imagingbook/parboiled/matchers/ProxyMatcher.<init> ()V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithoutAction : Limagingbook/parboiled/Rule;\n" +
                "    ALOAD 0\n" +
                "    INVOKESPECIAL imagingbook/parboiled/transform/TestParser.RuleWithoutAction ()Limagingbook/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L1\n" +
                "    LDC \"RuleWithoutAction\"\n" +
                "    INVOKEINTERFACE imagingbook/parboiled/Rule.label (Ljava/lang/String;)Limagingbook/parboiled/Rule; (itf)\n" +
                "   L1\n" +
                "    DUP_X1\n" +
                "    CHECKCAST imagingbook/parboiled/matchers/Matcher\n" +
                "    INVOKEVIRTUAL imagingbook/parboiled/matchers/ProxyMatcher.arm (Limagingbook/parboiled/matchers/Matcher;)V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithoutAction : Limagingbook/parboiled/Rule;\n" +
                "    ARETURN\n");

        AsmTestUtils.assertTraceDumpEquality(processMethod("RuleWithNamedLabel", processors), "" +
                "  @Limagingbook/parboiled/annotations/Label;(value=\"harry\")\n" +
                "    ALOAD 0\n" +
                "    GETFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithNamedLabel : Limagingbook/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L0\n" +
                "    ARETURN\n" +
                "   L0\n" +
                "    POP\n" +
                "    NEW imagingbook/parboiled/matchers/ProxyMatcher\n" +
                "    DUP\n" +
                "    INVOKESPECIAL imagingbook/parboiled/matchers/ProxyMatcher.<init> ()V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithNamedLabel : Limagingbook/parboiled/Rule;\n" +
                "    ALOAD 0\n" +
                "    INVOKESPECIAL imagingbook/parboiled/transform/TestParser.RuleWithNamedLabel ()Limagingbook/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L1\n" +
                "    LDC \"harry\"\n" +
                "    INVOKEINTERFACE imagingbook/parboiled/Rule.label (Ljava/lang/String;)Limagingbook/parboiled/Rule; (itf)\n" +
                "   L1\n" +
                "    DUP_X1\n" +
                "    CHECKCAST imagingbook/parboiled/matchers/Matcher\n" +
                "    INVOKEVIRTUAL imagingbook/parboiled/matchers/ProxyMatcher.arm (Limagingbook/parboiled/matchers/Matcher;)V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithNamedLabel : Limagingbook/parboiled/Rule;\n" +
                "    ARETURN\n");

        AsmTestUtils.assertTraceDumpEquality(processMethod("RuleWithLeaf", processors), "" +
                "    ALOAD 0\n" +
                "    GETFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithLeaf : Limagingbook/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L0\n" +
                "    ARETURN\n" +
                "   L0\n" +
                "    POP\n" +
                "    NEW imagingbook/parboiled/matchers/ProxyMatcher\n" +
                "    DUP\n" +
                "    INVOKESPECIAL imagingbook/parboiled/matchers/ProxyMatcher.<init> ()V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithLeaf : Limagingbook/parboiled/Rule;\n" +
                "    ALOAD 0\n" +
                "    INVOKESPECIAL imagingbook/parboiled/transform/TestParser.RuleWithLeaf ()Limagingbook/parboiled/Rule;\n" +
                "    DUP\n" +
                "    IFNULL L1\n" +
                "    LDC \"RuleWithLeaf\"\n" +
                "    INVOKEINTERFACE imagingbook/parboiled/Rule.label (Ljava/lang/String;)Limagingbook/parboiled/Rule; (itf)\n" +
                "   L1\n" +
                "    DUP\n" +
                "    IFNULL L2\n" +
                "    INVOKEINTERFACE imagingbook/parboiled/Rule.suppressNode ()Limagingbook/parboiled/Rule; (itf)\n" +
                "   L2\n" +
                "    DUP_X1\n" +
                "    CHECKCAST imagingbook/parboiled/matchers/Matcher\n" +
                "    INVOKEVIRTUAL imagingbook/parboiled/matchers/ProxyMatcher.arm (Limagingbook/parboiled/matchers/Matcher;)V\n" +
                "    DUP\n" +
                "    ALOAD 0\n" +
                "    SWAP\n" +
                "    PUTFIELD imagingbook/parboiled/transform/TestParser$$parboiled.cache$RuleWithLeaf : Limagingbook/parboiled/Rule;\n" +
                "    ARETURN\n");
    }

}
