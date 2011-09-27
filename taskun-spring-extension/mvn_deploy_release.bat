@echo off
mvn clean -DaltDeploymentRepository=release-repo::default::file:"%HOMEPATH%/github/seratch.github.com/mvn-repo/releases" clean deploy
