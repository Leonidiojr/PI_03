package application;

/**
 *
 * @author leoni
 */
public class Constants {    
    public static final String HOST = "localhost"; // ou o IP do servidor de banco de dados
    public static final String PORT = "5432"; // porta padrão do PostgreSQL
    public static final String DB_NAME = "alquimia"; // nome do banco de dados
    public static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/";    
    public static final String USER = "postgres";
    public static final String PASSWORD = "pi_l2kw@2024";
    public static final String DB_URL = Constants.FULL_DB_URL;    
    public static final String FULL_DB_URL = URL +  "alquimia";
}
