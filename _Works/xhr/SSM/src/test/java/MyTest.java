import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xhr.bean.UserBaseData;
import com.xhr.bean.UserSeenList;
import com.xhr.controller.UserController;
import com.xhr.dao.ListMapper;
import com.xhr.dao.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class MyTest {
    @Autowired
    UserMapper userMapper;


    ListMapper listMapper;

    @Autowired
    public void setListMapper(ListMapper listMapper) {
        this.listMapper = listMapper;
    }

    @Test
    public void testList() {
/*        List<UserSeenList> root = listMapper.getAllByUsername("root");
        System.out.println(root);*/

        Date date1 = new Date();
        java.sql.Date date = new java.sql.Date(date1.getTime());

        System.out.println(listMapper.getOne(1));


    }


    @Test
    public void a() {
        System.out.println("333");
        UserBaseData root = userMapper.getUserBaseDataByUsername("root");
        System.out.println(root);
    }

    @Test
    public void Token() {
        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .withAudience("ahhTou")
                .sign(Algorithm.HMAC256("111111"));

        System.out.println(token);
        System.out.println("====");


    }
}
