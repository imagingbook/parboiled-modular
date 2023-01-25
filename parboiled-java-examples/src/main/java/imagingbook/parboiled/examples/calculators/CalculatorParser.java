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

package imagingbook.parboiled.examples.calculators;

import imagingbook.parboiled.Rule;
import imagingbook.parboiled.common.StringUtils;
import imagingbook.parboiled.parser.BaseParser;
import imagingbook.parboiled.parser.Parboiled;
import imagingbook.parboiled.parserunners.RecoveringParseRunner;
import imagingbook.parboiled.support.ParsingResult;
import imagingbook.parboiled.support.ToStringFormatter;
import imagingbook.parboiled.trees.GraphNode;

import java.util.Scanner;

import static imagingbook.parboiled.errors.ErrorUtils.printParseErrors;
import static imagingbook.parboiled.support.ParseTreeUtils.printNodeTree;
import static imagingbook.parboiled.trees.GraphUtils.printTree;

/**
 * Base class of all calculator parsers in the org.parboiled.examples.calculators package.
 * Simply adds the public static main entry point.
 *
 * @param <V> the type of the main value object created by the parser
 */
public abstract class CalculatorParser<V> extends BaseParser<V> {

    public abstract Rule InputLine();

    @SuppressWarnings({"unchecked"})
    public static <V, P extends CalculatorParser<V>> void main(Class<P> parserClass) {
        CalculatorParser<V> parser = Parboiled.createParser(parserClass);

        while (true) {
            System.out.print("Enter a calculators expression (single RETURN to exit)!\n");
            String input = new Scanner(System.in).nextLine();
            if (StringUtils.isEmpty(input)) break;

            ParsingResult<?> result = new RecoveringParseRunner(parser.InputLine()).run(input);

            if (result.hasErrors()) {
                System.out.println("\nParse Errors:\n" + printParseErrors(result));
            }

            Object value = result.parseTreeRoot.getValue();
            if (value != null) {
                String str = value.toString();
                int ix = str.indexOf('|');
                if (ix >= 0) str = str.substring(ix + 2); // extract value part of AST node toString()
                System.out.println(input + " = " + str + '\n');
            }
            if (value instanceof GraphNode) {
                System.out.println("\nAbstract Syntax Tree:\n" +
                        printTree((GraphNode) value, new ToStringFormatter(null)) + '\n');
            } else {
                System.out.println("\nParse Tree:\n" + printNodeTree(result) + '\n');
            }
        }
    }

}
