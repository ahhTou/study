package U10_to_improve_U7.utils;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了
 * 开启事务
 * 提交事务
 * 回滚事务
 * 释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            System.out.println("开启业务出现bug");
            throwables.printStackTrace();
        }
    }
    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException throwables) {
            System.out.println("提交业务出现bug");
            throwables.printStackTrace();
        }

    }
    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException throwables) {
            System.out.println("回滚业务出现bug");
            throwables.printStackTrace();
        }

    }
    /**
     * 释放连接
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException throwables) {
            System.out.println("释放业务出现bug");

            throwables.printStackTrace();
        }

    }

}
