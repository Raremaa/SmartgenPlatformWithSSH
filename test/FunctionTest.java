import com.zing.dao.CollectioninfoDao;
import com.zing.dao.CreativeprojectDao;
import com.zing.dao.UserDao;
import com.zing.dao.impl.UserDaoImpl;
import com.zing.pojo.*;
import com.zing.queryparam.CollectioninfoQueryParam;
import com.zing.queryparam.ProductQueryParam;
import com.zing.serviceDao.*;
import com.zing.serviceDao.impl.UserServiceDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FunctionTest {

    @Autowired
    private UserServiceDao userServiceDao;
    @Test
    public void funUser1() throws Exception {
        User user = new User();
        user.setUserName("weq");
        user.setUserSex(1);
        user.setUserPhone("2372312");
        user.setUserPassword("134124124124");
        user.setUserHeadPortrait("123123123123141");
        user.setUserIdentity(1);
        userServiceDao.save(user);
    }

    @Autowired
    private CreativeprojectServiceDao creativeprojectServiceDao;
    @Test
    public void funProduct1() throws Exception{
        Creativeproject creativeproject = new Creativeproject();
        creativeproject.setId(1);
        creativeproject.setCreprojectContent("实例");
        System.err.println(creativeprojectServiceDao.update(creativeproject));
    }

    @Autowired
    private CreativeremarkServiceDao creativeremarkServiceDao;
    @Test
    public void fun1() throws Exception{
        Integer userId = 2;
        Integer creProjectId = 2;
        Creativeremark c = creativeremarkServiceDao.getByUserAndCreProject(userId,creProjectId);
        System.err.println(c);
    }

    @Autowired
    private CollectioninfoDao collectioninfoDao;


    @Autowired
    private UserDao userDao;
    @Test
    public void fun4() throws Exception{
        User user = userDao.getUserById(6);
        System.err.println(user);
    }


    @Test
    public void fun5() throws Exception{
        CollectioninfoQueryParam queryParam = new CollectioninfoQueryParam();
        queryParam.setCondition("user.id =2");
        System.err.println(collectioninfoDao.getCount(queryParam));
    }

    @Autowired
    private CollectioninfoServiceDao collectioninfoServiceDao;
    @Test
    public void fun6() throws Exception{
        Collectioninfo c = new Collectioninfo();
        Product p = new Product();
        p.setId(1);
        User u = new User();
        u.setId(7);
        c.setUser(u);
        c.setProduct(p);
        System.err.println(collectioninfoServiceDao.save(c));
    }

    @Test
    public void fun7()throws Exception{
        User u = new User();
        u.setId(1);
        System.err.println(collectioninfoDao.getProductByUserId(1));
    }
}
