package me.zhengjie;

import me.zhengjie.entity.User;
import me.zhengjie.mapper.UserMapper;
import me.zhengjie.modules.security.service.UserDetailsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginCacheTest {

    @Resource(name = "userDetailsService")
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private UserMapper userMapper;

    @Test
    public void testCache() {
        long start1 = System.currentTimeMillis();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            userDetailsService.loadUserByUsername("admin");
        }
        long end1 = System.currentTimeMillis();
        //关闭缓存
        userDetailsService.setEnableCache(false);
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            userDetailsService.loadUserByUsername("admin");
        }
        long end2 = System.currentTimeMillis();
        System.out.print("使用缓存：" + (end1 - start1) + "毫秒\n 不使用缓存：" + (end2 - start2) + "毫秒");
    }
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
