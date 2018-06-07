import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FunctionTest {
    @Resource(name = "sessionFactory")
    private SessionFactory sf;
    @Test
    //测试spring管理sessionFactory
    public void fun1(){
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        //------------------------------------------------

        //------------------------------------------------
        tx.commit();
        session.close();
    }
}
