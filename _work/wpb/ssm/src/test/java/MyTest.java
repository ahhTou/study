import com.ahhTou.bean.Anime;
import com.ahhTou.bean.User;
import com.ahhTou.dao.AnimeMapper;
import com.ahhTou.dao.AnimeTypesMapper;
import com.ahhTou.dao.LoginMapper;
import com.ahhTou.untils.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class MyTest {


    @Autowired
    AnimeMapper animeMapper;

    @Autowired
    AnimeTypesMapper animeTypesMapper;

    @Test
    public void testTypes1(){

        System.out.println(animeTypesMapper.getTypesByAnimeId(4));
    }

    @Test
    public void testList1() {
        ArrayList<Anime> onePage = animeMapper.getOnePage(1);
        for (Anime a : onePage
        ) {
            System.out.println(a);
        }
    }


    @Test
    public void testToken() {
        String ahhTou = Token.create("ahhTou");
        System.out.println(ahhTou);

        System.out.println(Token.verify("123"));
    }

    @Autowired
    LoginMapper loginMapper;

    @Test
    public void testLogin() {
        User user = new User();

        System.out.println();
        user.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
        user.setUsername("root");

        System.out.println(user);

        User user1 = loginMapper.loginCheck(user);

        System.out.println(user1);


    }

    @Test
    public void a() {
        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 10; b++) {
                // 让个位减1
                System.out.println("123");
            }
            // 让十位减1
        }
    }

}
