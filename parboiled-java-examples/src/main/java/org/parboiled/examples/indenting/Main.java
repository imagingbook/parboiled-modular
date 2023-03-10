package org.parboiled.examples.indenting;

import org.parboiled.buffers.IndentDedentInputBuffer;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parser.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

import static org.parboiled.support.ParseTreeUtils.printNodeTree;

public class Main {

    public static void main(String[] args) {
        SimpleIndent parser = Parboiled.createParser(SimpleIndent.class);
        String input = "NodeA \n\tNodeB\n\tNodeC \n\t\tNodeD \nNodeE";

        ParsingResult<?> result = new ReportingParseRunner(parser.Parent())
                .run(new IndentDedentInputBuffer(input.toCharArray(), 2, ";", true, true));

        if (!result.parseErrors.isEmpty()) {
            System.out.println(ErrorUtils.printParseError(result.parseErrors
                    .get(0)));
        } else {
            System.out.println("NodeTree: " + ParseTreeUtils.printNodeTree(result) + '\n');
            Object value = result.parseTreeRoot.getValue();
            System.out.println(value.toString());
        }

    }
}
