import com.ahhTou.bean.Anime;
import com.ahhTou.bean.User;
import com.ahhTou.dao.AnimeMapper;
import com.ahhTou.dao.AnimeTypesMapper;
import com.ahhTou.dao.LoginMapper;
import com.ahhTou.untils.MyObjectMapper;
import com.ahhTou.untils.Token;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.sql.Date;
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
    public void typeTest() {
        List<String> allTypes = animeTypesMapper.getAllTypes();
        System.out.println(allTypes);
    }

    @Test
    public void del1() {
/*        Boolean aBoolean = animeMapper.delAnimeById(101);
        System.out.println(aBoolean);*/

        Anime anime = new Anime();
        anime.setTitle("神之塔");
        Date date = new Date(System.currentTimeMillis());
        anime.setEndTime(date);
        Boolean aBoolean = animeMapper.addOneAnime(anime);
        System.out.println(aBoolean);

    }


    @Test
    public void x1() {
/*        ObjectMapper objectMapper = new ObjectMapper();
        // 转换为格式化的json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);*/
        ObjectMapper objectMapper = MyObjectMapper.createMyObjectMapper();

        List<Anime> onePage = animeMapper.getOnePage(1);
        System.out.println(onePage.get(0));
        try {
            System.out.println(objectMapper.writeValueAsString(onePage));
        } catch (Exception e) {
            System.out.println("出错了");
        }
    }

    @Test
    public void pages1() {
        System.out.println(animeMapper.getHowMuchColumn());
    }

    @Test
    public void testTypes1() {

        System.out.println(animeTypesMapper.getTypesByAnimeId(4));
    }

    @Test
    public void testList1() {
        List<Anime> onePage = animeMapper.getOnePage(1);
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
