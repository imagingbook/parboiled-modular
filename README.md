# parboiled-modular

This is a modularized version of the brilliant _parboiled_ Java/Scala PEG parser implementation
developed by Mathias Doenitz, refactored from https://github.com/sirthias/parboiled (Version 1.4.1, Jan. 2023).
The original *parboiled* library provides *"for lightweight and easy-to-use, yet powerful and elegant parsing of
arbitrary input text based on Parsing expression grammars (PEGs)"*. See <https://github.com/sirthias/parboiled/wiki>
for additional details.

The features of _this_ implementation are:

* Builds on the original *parboiled* project and provides the same basic functionality (see exceptions below).
* Supports the *Java 9 module system* by defining named modules with controlled imports and exports. 
  Note that the original library could not be imported into a modularized Java project due to overlapping packages
  in the ``parboiled-core`` and ``parboiled-java`` modules. Packages are now separated but
  some classes in ``parboiled-java`` had to be relocated to the new ``parboiled.parser`` package.
* Module ``parboiled-core`` is 1:1 compatible with the original library.
  In module ``parboiled-java``, the following classes now reside in package ``parboiled.parser``:
  * ``BaseActions``, 
  * ``BaseParser``,
  * ``Parboiled``.
* All Java testing is based on ``JUnit4``. All required Java packages have been updated to current versions if possible.
* The build process is based on Maven.
* The original *Scala* implementation has been refactored and is included as well. Note that the Scala part
  may be dropped in a future release.


## Please see

* <https://repo1.maven.org/maven2/org/parboiled/> for download access to the artifacts
* <https://github.com/sirthias/parboiled/wiki> for all documentation
* <https://sirthias.github.io/parboiled/api/core> for the parboiled-core API javadoc
* <https://sirthias.github.io/parboiled/api/java> for the parboiled-java API javadoc
* <https://sirthias.github.io/parboiled/api/scala> for the parboiled-scala API scaladoc
* <https://github.com/sirthias/parboiled/wiki/Patch-Policy> for the Patch Policy

## API Documentation

* [**parboiled-core**](https://imagingbook.github.io/parboiled-modular/parboiled-core/javadoc)
* [**parboiled-java**](https://imagingbook.github.io/parboiled-modular/parboiled-java/javadoc)
* [**parboiled-java-examples**](https://imagingbook.github.io/parboiled-modular/parboiled-java-examples/javadoc)
  
## A note on the unit tests

* Some of the tests that read input strings from files or compare to results given in files require
  that line endings (EOLs) must remain *unchanged* by GIT. Otherwise tests may fail.
  The same is true for scala source files that
  contain literal strings for either input or parsing results.
  EOL conversion should be suppressed by the repo's ``.gitattributes`` settings.
  However, it may be necessary to also change the global GIT settings on your client machine
  by ``git config --global core.autocrlf false``.
