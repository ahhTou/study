package U10_to_improve_U7.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 链接工具类，它用于从数据源中获取一个连接，并实现和线程的绑定
 */

public class ConnectionUtils {

    private ThreadLocal<Connection> t1 =
            new ThreadLocal<>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadConnection(){
        try {
            // 1.先从ThreadLocal上获取
            Connection conn = t1.get();
            // 2.判断当前线程中是否有链接
            if (conn == null){
                // 3.从数据源中获取一个连接，并存入到ThreadLocal
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把链接和线程解绑
     */
    public void removeConnection(){
        t1.remove();
    }
}
