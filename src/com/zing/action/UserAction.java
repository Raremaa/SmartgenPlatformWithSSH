package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.pojo.User;
import com.zing.serviceDao.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.io.Serializable;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    private User user = new User();
    @Autowired
    private UserServiceDao userServiceDao;
    @Override
    public User getModel() {
        return this.user;
    }

    public User getUserById(Serializable id){
        try {
            if(userServiceDao.getUserById(id) == null ){
                return null;
            }else{
                return userServiceDao.getUserById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
