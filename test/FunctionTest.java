import com.zing.dao.UserDao;
import com.zing.dao.impl.UserDaoImpl;
import com.zing.pojo.User;
import com.zing.serviceDao.UserServiceDao;
import com.zing.serviceDao.impl.UserServiceDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FunctionTest {

    @Autowired
    private UserDao userDao;
    @Test
    public void fun2(){
        User user = null;
        try {
            user = userDao.getUserById(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Autowired
    private UserServiceDao userServiceDao;
    @Test
    public void fun1() throws Exception {
        User user = userServiceDao.getUserById(6);
        System.err.println(user);
    }
}
