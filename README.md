# ch.vorburger.karaf.simple

Demo illustrating how to run custom Apache Karaf (OSGi runtime middleware) commands without actually requiring Karaf and OSGi.

See [opendaylight-simple](https://github.com/vorburger/opendaylight-simple) for more background what motivated this.

## Build

   mvn clean install

## Traditional Karaf usage

    karaf@root()> bundle:install -s file:///home/YOURUID/.m2/repository/ch/vorburger/karaf/command/1.0.0-SNAPSHOT/ch.vorburger.karaf.command-1.0.0-SNAPSHOT.jar
    karaf@root(test)> test:test --help
    karaf@root(test)> test:test -o opt arg
    Executing command test
    Option: opt
    Argument: arg

## New simple distribution package usage

    java -jar distribution/simple/target/distribution-1.0.0-SNAPSHOT-jar-with-dependencies.jar

    karaf@root(test)> test:test -o opt arg
    Executing command test
    Option: opt
    Argument: arg

### TODO

1. How to support SSH server in simple?

2. How to enable stopping the simple dist? ;)  Ctrl-D does work, usual commands don't:

       karaf@root()> exit
       karaf@root()> logout      
       Command not found: logout  
       karaf@root()> system:shutdown
       Command not found: system:shutdown

3. How to add `--help` in simple?

       karaf@root()> test:test --help
       karaf@root()> help test:test
