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

import imagingbook.parboiled.common.Preconditions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static imagingbook.parboiled.transform.AsmTestUtils.*;
import static imagingbook.parboiled.common.Preconditions.*;

// @Test(groups = "primary")
public class ParserExtensionVerificationTest {

    //@Test     // wilbur: test deactivated
    public void verifyTestParserExtension() throws Exception {
        ParserClassNode classNode = ParserTransformer.extendParserClass(TestParser.class);
        AsmTestUtils.verifyIntegrity(classNode.name, classNode.getClassCode());

        Set<String> validGroups = new HashSet<String>();
        for (RuleMethod method : classNode.getRuleMethods().values()) {
            for (InstructionGroup group : method.getGroups()) {
                String internalName = group.getGroupClassType().getInternalName();
                byte[] classCode = group.getGroupClassCode();
                if (!validGroups.contains(internalName)) {
                    Preconditions.checkState(classCode != null);
                    AsmTestUtils.verifyIntegrity(internalName, classCode);
                    validGroups.add(internalName);
                }
            }
        }
    }

}
