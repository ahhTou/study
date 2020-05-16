package U11_Proxy.proxy;

public class Producer implements IProducer{

    /**
     * 销售
     * @param money
     */
    @Override
    public void saleProduct(float money){
        System.out.println("销售产品并拿到钱" + money);
    }

    /**
     * 售后
     * @param money
     */
    @Override
    public void afterService(float money){
        System.out.println("提供售后服务" + money);
    }


}
