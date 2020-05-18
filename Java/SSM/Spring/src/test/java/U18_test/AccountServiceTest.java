package U18_test;


import U18_Annotation_tx_Plus.config.SpringConfiguration;
import U18_Annotation_tx_Plus.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfiguration.class)

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
