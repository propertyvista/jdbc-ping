package com.devpv;

import picocli.CommandLine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCPingMain {

    public static void main(String[] args) {
        JDBCPingCommand cmd = new JDBCPingCommand();
        CommandLine commandLine = new CommandLine(cmd);
        if (args.length == 0) {
            commandLine.usage(System.out);
            return;
        }

        commandLine.parseArgs(args);
        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        }
        testConnection(cmd.driver, cmd.url, cmd.user, cmd.password);
        System.exit(0);
    }

    public static void testConnection(String driver, String connectionUrl, String username, String password) {
        System.out.println("Testing connectionUrl:" + connectionUrl);
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        java.sql.Connection connection = null;
        try {
            if (driver != null) {
                Class.forName(driver);
            }
            connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connected to DB " + getDbInfo(connection));
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            SQLUtils.closeQuietly(connection);
        }
    }

    private static String getDbInfo(Connection connection) throws SQLException {
        DatabaseMetaData dbMeta = connection.getMetaData();
        return dbMeta.getDatabaseProductName() + " " + dbMeta.getDatabaseMajorVersion() + "." + dbMeta.getDatabaseMinorVersion();
    }
}
