package pers.zhangyang.itemtrade.util;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class InvocationUtil {
    public static Object getService(Object service){
        //这里返回的是代理类对象
        return new TransactionInvocationHandler(service).getProxy();
    }
}


 class  TransactionInvocationHandler implements InvocationHandler {

    private Object target;

    public TransactionInvocationHandler(Object target){

        this.target = target;

    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



        Connection connection= DatabaseUtil.getConnection();
        Object obj=null;
        try{
            obj = method.invoke(target, args);

            connection.commit();
        }catch(Exception e){
            connection.rollback();
            throw e.getCause();
            //处理的是什么异常，继续往上抛什么异常
        }finally{
            connection.close();
        }

        return obj;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);

    }

}