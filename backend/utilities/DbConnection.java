package bank.backend.utilities;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null){
            dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl(DbConfig.URL);
            ((MysqlDataSource) dataSource).setUser(DbConfig.username);
            ((MysqlDataSource) dataSource).setPassword(DbConfig.password);

        }

        return dataSource.getConnection();

    }


}
