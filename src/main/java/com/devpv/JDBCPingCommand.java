package com.devpv;


import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Unmatched;

@Command(description = "JDBC Ping", name = "jdbc-ping.jar", sortOptions = false, mixinStandardHelpOptions = true)
public class JDBCPingCommand {

    @Option(names = "--url", required = true)
    String url;

    @Option(names = {"--user", "-u"})
    String user;

    @Option(names = {"--password", "-p" })
    String password;

    @Option(names = "--driver")
    String driver;
}
