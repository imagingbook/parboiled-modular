package imagingbook.parboiled.examples.indenting;

import static imagingbook.parboiled.support.ParseTreeUtils.printNodeTree;

import imagingbook.parboiled.parser.Parboiled;
import imagingbook.parboiled.parserunners.ReportingParseRunner;
import imagingbook.parboiled.parser.Parboiled;
import imagingbook.parboiled.buffers.IndentDedentInputBuffer;
import imagingbook.parboiled.errors.ErrorUtils;
import imagingbook.parboiled.parserunners.ReportingParseRunner;
import imagingbook.parboiled.support.ParsingResult;

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
            System.out.println("NodeTree: " + printNodeTree(result) + '\n');
            Object value = result.parseTreeRoot.getValue();
            System.out.println(value.toString());
        }

    }
}
