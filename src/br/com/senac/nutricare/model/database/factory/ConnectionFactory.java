/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.nutricare.model.database.factory;

import br.com.senac.nutricare.model.exceptions.DatabaseException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import br.com.senac.nutricare.utils.LoggerUtil;

/**
 * @author Alexandre Finger Sobrinho
 * @author Alexandre Pereira Souza
 */
public class ConnectionFactory {

    private static String url;
    private static String driver;
    private static String user;
    private static String pswd;
    private static LoggerUtil logger;

    public static Connection getConnection(String url, String driver, String user, String pswd) throws DatabaseException {

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pswd);
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException("Erro ao carregar Driver: " + ex.getMessage());
        } catch (SQLException ex) {
            throw new DatabaseException("Erro na conexÃ£o com o banco! " + ex.getMessage());
        }
    }

    public static Connection getConnection() throws DatabaseException {
        loadProperties();
        return getConnection(url, driver, user, pswd);
    }

    private static void loadProperties() {
        Properties p = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(new File("conf.properties"));
            p.load(fis);

            url = p.getProperty("url");
            driver = p.getProperty("driver");
            user = p.getProperty("user");
            pswd = p.getProperty("pswd");

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                logger.error("Erro na leitura do arquivo 'conf.properties'" + e.getMessage());
                logger.info("Verifique se o arquivo existe no diretório: " + System.getProperty("????"));
            }
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception ex) {
            logger.error("Problema ao fechar conexão! " + ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, Statement stmt) {
        try {
            if (con != null) {
                con.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            logger.error("Problema ao fechar conexão! " + ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            logger.error("Problema ao fechar conexão! " + ex.getMessage());
        }
    }

    public static void closeConnection(Statement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            logger.error("Problema ao fechar conexão! " + ex.getMessage());
        }
    }

    public static void closeConnection(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            logger.error("Problema ao fechar conexão! " + ex.getMessage());
        }
    }

    public static void closeConnection(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            logger.error("Problema ao fechar conexão! " + ex.getMessage());
        }
    }
}
