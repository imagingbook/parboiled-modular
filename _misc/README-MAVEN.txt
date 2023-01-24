Maven setup:

Everything is done from within Eclipse (no external Maven installation is
needed).

*****************************************************************************
Checking multi-module projects
*****************************************************************************
See https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
At location of root POM:

  mvn validate
  mvn compile
  mvn package

*****************************************************************************
Show dependency tree
*****************************************************************************

	mvn dependency:tree
	
*****************************************************************************
Show variables/properties (with antrun)
*****************************************************************************

	mvn initialize

*****************************************************************************
Generate JavaDoc
*****************************************************************************

	mvn javadoc:javadoc -Dmaven.javadoc.skip=false
	mvn javadoc:javadoc -Dimagingbook.skipjavadoc=false
	
	mvn javadoc:aggregate -Dimagingbook.skipjavadoc=false
	mvn javadoc:aggregate-jar  -Dimagingbook.skipjavadoc=false  (CHECK how to deploy this!)
	
Note: maven.javadoc.skip=true by default to avoid Javadoc generation during install.
	
*****************************************************************************
RELEASE using the 'versions' plugin (simple)
*****************************************************************************
in shell (imagingbook-super):

	mvn versions:set -DprocessAllModules=true -DgenerateBackupPoms=false -DnewVersion=6.0.0

in Eclipse:
	imagingbook-super: Maven - update project
	imagingbook-public: clean
	imagingbook-public: install
	imagingbook-super: clean
	imagingbook-super: install

in shell or Eclipse (imagingbook-public);
	mvn javadoc:aggregate -Dimagingbook.skipjavadoc=false
	
Commit/push everything (commit message "v6.0.0")
Add tag '6.0.0' to all repos (SmartGit: Branch->Add Tag)

do DEPLOY (see below)

Step version number (as in step 1):

	mvn versions:set -DprocessAllModules=true -DgenerateBackupPoms=false -DnewVersion=6.0.1-SNAPSHOT


*****************************************************************************
DEPLOY to Maven Central (wilbur@ieee.org)
*****************************************************************************

in imagingbook-public:

	( mvn deploy -Dmaven.test.skip=true -Dmaven.javadoc.skip=false -Dimagingbook.gpgSkip=false )
	mvn deploy -Dmaven.test.skip=true -Dimagingbook.skipjavadoc=false -Dimagingbook.gpgSkip=false    <---

NOTE: In imagingbook-parent-pom maven.javadoc.skip=true and gpg.skip=true by default!
	Do not run in Eclipse, sind gpg throws null-pointer exception!
	SNAPSHOT versions are automatically deployed to local DEPLOY directory.
	RELEASE versions are deployed to Sonatype.
	Always check if JavaDoc files and GPG signature files (ASC) are included!
	
On Sonatype:
	
	Log into https://s01.oss.sonatype.org/#stagingRepositories (wilbur/ro..):
		check upload + "Close" (will send email with report) + "Release" 
		
	Check on https://mvnrepository.com/artifact/com.imagingbook

Sonatype/Nexus repository manager
	https://central.sonatype.org/publish/
	https://central.sonatype.org/publish/publish-maven/
	https://central.sonatype.org/news/20210223_new-users-on-s01/
	https://s01.oss.sonatype.org/

Testing deployment:
	https://stackoverflow.com/questions/62018354/how-to-test-maven-deploy


*****************************************************************************
Check for new artefact versions:
*****************************************************************************
see https://www.mojohaus.org/versions-maven-plugin/examples/display-dependency-updates.html

in imagingbook-public:
	mvn versions:display-dependency-updates