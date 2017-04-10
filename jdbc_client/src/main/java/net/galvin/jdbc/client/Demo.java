package net.galvin.jdbc.client;

import net.galvin.jdbc.comm.pojo.User;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Demo {

    public static void main(String[] args) {
        /*Map<String,Object> map = new HashMap<String,Object>();
        map.put("abc","abc");
        map.put("abC","abC");
        System.out.println(map);*/

        /*PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(User.class);
        for (PropertyDescriptor pd : pds) {
            if (pd.getWriteMethod() != null) {
                String cName = "{ pd.getName():"+pd.getName()+",pd.getDisplayName():"+pd.getDisplayName()+" }";
                System.out.println(cName);
            }
        }*/

        try {
            Constructor<User> constructor = User.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            User user = constructor.newInstance();
            user.setId(1l);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
