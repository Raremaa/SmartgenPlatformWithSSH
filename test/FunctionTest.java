import com.zing.dao.CreativeprojectDao;
import com.zing.dao.UserDao;
import com.zing.dao.impl.UserDaoImpl;
import com.zing.pojo.Creativeproject;
import com.zing.pojo.User;
import com.zing.queryParam.CreativeprojectQueryParam;
import com.zing.serviceDao.UserServiceDao;
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

//    @Autowired
//    private CreativeprojectDao creativeprojectDao;
//    @Test
//    public void funUser2()throws Exception{
//        CreativeprojectQueryParam queryParam = new CreativeprojectQueryParam();
//        queryParam.setCondition("1=1");
//        queryParam.setOrderBy("userId");
//        queryParam.setOrderByInTurn("DESC");
//        queryParam.setPage(3);
//        queryParam.setPageSize(3);
//        List<Creativeproject> creativeproject = creativeprojectDao.getCreativeprojectList(queryParam);
//        System.err.println(creativeproject.get(0));
//        System.err.println(creativeproject.size());
//    }
}
