# parboiled-modular
This is a modularized version of the ``parboiled`` Java/Scala PEG parser implementation
developed by Mathias Doenitz, refactored from Version 1.4.1 (https://github.com/sirthias/parboiled, Jan. 2023).

## API Documentation

* [**parboiled-core**](https://imagingbook.github.io/parboiled-modular/parboiled-core/javadoc)
* [**parboiled-java**](https://imagingbook.github.io/parboiled-modular/parboiled-java/javadoc)
* [**parboiled-java-examples**](https://imagingbook.github.io/parboiled-modular/parboiled-java-examples/javadoc)
  
## Notes

* Some of the tests that read input strings from files or compare to results given in files require
  that line endings (EOLs) must remain *unchanged* by GIT. Otherwise tests may fail.
  The same is true for scala source files that
  contain literal strings for either input or parsing results.
  EOL conversion should be suppressed by the repo's ``.gitattributes`` settings.
  However, it may be necessary to also change the global GIT settings on your client machine
  by ``git config --global core.autocrlf false``.
