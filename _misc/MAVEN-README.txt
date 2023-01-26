Check for new artefact versions:

	mvn versions:display-dependency-updates
	
Version change:

	mvn versions:set -DprocessAllModules=true -DgenerateBackupPoms=false -DnewVersion=6.0.0

Javadoc generation:

	mvn javadoc:javadoc -Dmaven.javadoc.skip=false


Deployment (https://s01.oss.sonatype.org/#stagingRepositories):

	mvn deploy -Dmaven.test.skip=true -Dmaven.javadoc.skip=false -Dimagingbook.gpgSkip=false )

	Check on https://mvnrepository.com/artifact/com.imagingbook
