# parboiled-modular (lighweight & elegant PEG parser)

This is a modularized version of the brilliant _parboiled_ Java/Scala PEG parser implementation
developed by Mathias Doenitz, refactored from https://github.com/sirthias/parboiled (Version 1.4.1, Jan. 2023).
The original *parboiled* library provides *"for lightweight and easy-to-use, yet powerful and elegant parsing of
arbitrary input text based on Parsing expression grammars (PEGs)"*. See <https://github.com/sirthias/parboiled/wiki>
for additional details.

The features of _this_ implementation are:

* Builds on the original *parboiled* project and provides the same basic functionality (see exceptions below).
* Supports the *Java 9 module system* by defining named modules with controlled imports and exports. 
  Note that the original library could not be imported into a modularized Java project due to package overlaps
  in modules `parboiled-core` and ``parboiled-java``. To separate package `parboiled` the associated classes
  in ``parboiled-java`` were relocated to a _new package_ named `parboiled.parser`.
* Module ``parboiled-core`` is 1:1 compatible with the original library.
* In module ``parboiled-java``, the following classes moved from `parboiled` to the new package `parboiled.parser`:
  * `BaseActions`,
  * `BaseParser`,
  * `Parboiled`.
* All Java testing is based on ``JUnit4``. All required Java packages have been updated to current versions if possible.
* The build process is based on Maven, all Java modules are available as Maven artifacts on Central.
* The original *Scala* implementation (library and examples) has been refactored and is included here as well. (It is not
  published as a Maven artifact). Note that the Scala part
  may be dropped in a future release.

## Use With Maven

Each of the Java modules is available as a 
[Maven artifact on Maven Central](https://search.maven.org/search?q=g:com.imagingbook), that is,

* [**parboiled-core**](https://mvnrepository.com/artifact/com.imagingbook/parboiled-core)
* [**parboiled-java**](https://mvnrepository.com/artifact/com.imagingbook/parboiled-java)
* [**parboiled-java-examples**](https://mvnrepository.com/artifact/com.imagingbook/parboiled-java-examples)

For example, to use the `parboiled-core` module, include the following in your
project's `pom.xml`file:
```
<dependency>
  <groupId>com.imagingbook</groupId>
  <artifactId>parboiled-core</artifactId>
  <version>1.0.0</version>
</dependency>
```
Replace the number in `<version>...</version>` by the most current release version found on 
[Maven Central](https://mvnrepository.com/artifact/com.imagingbook/parboiled-core).



## API Documentation

* [**parboiled-core**](https://imagingbook.github.io/parboiled-modular/parboiled-core/javadoc)
* [**parboiled-java**](https://imagingbook.github.io/parboiled-modular/parboiled-java/javadoc)
* [**parboiled-java-examples**](https://imagingbook.github.io/parboiled-modular/parboiled-java-examples/javadoc)


## Examples

The examples in ``parboiled-java-examples`` are an excellent place to get started. 
Here is an excerpt from the ``CalculatorParser0`` class (note the imports from `imagingbook.parboiled`!):
```
package imagingbook.parboiled.examples.calculators;

import imagingbook.parboiled.parser.BaseParser;
import imagingbook.parboiled.Rule;
import imagingbook.parboiled.annotations.BuildParseTree;

/**
 * A basic calculator parser without any actions.
 */
@BuildParseTree
public class CalculatorParser0 extends CalculatorParser<Integer> {

    @Override
    public Rule InputLine() {
        return Sequence(Expression(), BaseParser.EOI);
    }

    Rule Expression() {
        return Sequence(Term(), ZeroOrMore(AnyOf("+-"), Term()));
    }

    Rule Term() {
        return Sequence(Factor(), ZeroOrMore(AnyOf("*/"), Factor()));
    }

    Rule Factor() {
        return FirstOf(Number(), Parens());
    }

    Rule Parens() {
        return Sequence('(', Expression(), ')');
    }

    Rule Number() {
        return OneOrMore(Digit());
    }

    Rule Digit() {
        return CharRange('0', '9');
    }

    //**************** MAIN ****************

    public static void main(String[] args) {
        main(CalculatorParser0.class);
    }
}
```


  
## Notes on Unit Tests

* Some unit tests in this project check against package/class names and/or Java object hash values and are thus sensitive
  to any package and class name changes within the library.
* Some of the tests that read input strings from files check againsto results provided in text files require
  that line endings (EOLs) must remain *unchanged* by GIT. Otherwise tests may fail.
  The same is true for scala source files that contain literal strings for either input or parsing results.
  EOL conversion should be suppressed by the repo's ``.gitattributes`` settings.
  However, it may be necessary to also change the global GIT settings on your client machine
  by ``git config --global core.autocrlf false``.
