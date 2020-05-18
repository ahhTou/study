package U14_AOP_Demo.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了
 * 开启事务
 * 提交事务
 * 回滚事务
 * 释放连接
 */
@Component("txManager")
@Aspect
public class TransactionManager {

    @Resource(name = "ConnectionUtils")
    private ConnectionUtils connectionUtils;

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

    @Pointcut("execution(*  U14_AOP_Demo.service.impl.*.*(..)))")
    private void pt(){}


    @Around("pt()")
    public Object aroundPrintLog(ProceedingJoinPoint pjb){
        Object rtValue = null;
        try{

            System.out.println("Start");
            this.beginTransaction();

            pjb.proceed(pjb.getArgs());

            this.commit();


            return rtValue;
        }catch (Throwable throwable){

            this.rollback();
            throw new RuntimeException(throwable);

        }finally {

            this.release();

        }
    }

}
