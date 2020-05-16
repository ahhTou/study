package U10_to_improve_U7.factory;

import U10_to_improve_U7.service.IAccountService;
import U10_to_improve_U7.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建service的代理对象的工厂
 */

public class BeanFactory {

    private IAccountService accountService;

    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取Service的代理对象
     *
     * @return
     */

    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(
                accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try {
                            System.out.println("Safe Mode");
                            //1.开启事务
                            txManager.beginTransaction();
                            System.out.println("OpenManger");
                            //2.执行操作
                            rtValue = method.invoke(accountService, args);
                            //3.提交事务
                            txManager.commit();
                            System.out.println("Commit……");
                            //4.返回结果
                            return rtValue;
                        } catch (Exception e) {
                            //5.回滚操作
                            txManager.rollback();
                            System.out.println("Err and rollBack");
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放操作
                            txManager.release();
                            System.out.println("Service End");
                        }
                    }
                }

        );
    }

}
