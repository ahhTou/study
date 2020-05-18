package U16_test;


import U16_spring_tx_api.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:U16_bean.xml")

public class AccountServiceTest {
    @Autowired
    private final IAccountService as = null;

    @Test
    public void testFindOne(){
        as.findAccountById(1);
    }

    @Test
    public void testTransfer() {
        as.transfer("aaa", "bbb", 100f);
    }

}
