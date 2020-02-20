package com.lifp.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lifp.mpdemo.entity.User;
import com.lifp.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MpdemoApplicationTests {

    /**
     * eq("name", "老王")--->name = '老王'  ne("name", "老王")--->name <> '老王'
     * gt("age", 18)--->age > 18   ge("age", 18)--->age >= 18
     * lt("age", 18)--->age < 18   le("age", 18)--->age <= 18
     * between("age", 18, 30)--->age between 18 and 30    notBetween("age", 18, 30)--->age not between 18 and 30
     * like("name", "王")--->name like '%王%'    notLike("name", "王")--->name not like '%王%'
     * likeLeft("name", "王")--->name like '%王'   likeRight("name", "王")--->name like '王%'
     * isNull("name")--->name is null    isNotNull("name")--->name is not null
     * in("age",{1,2,3})--->age in (1,2,3)    notIn("age",{1,2,3})--->age not in (1,2,3)
     * inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3)
     * notInSql("id", "select id from table where id < 3")--->age not in (select id from table where id < 3)
     * groupBy("id", "name")--->group by id,name     orderBy(true, true, "id", "name")--->order by id ASC,name ASC
     * orderByAsc("id", "name")--->order by id ASC,name ASC
     * orderByDesc("id", "name")--->order by id DESC,name DESC
     * having("sum(age) > 10")--->having sum(age) > 10   eq("id",1).or().eq("name","老王")--->id = 1 or name = '老王'
     *
     * @Author lifp
     * @Date 2020/2/20
     */
    @Test
    void testWrapper() {
        // 构建条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        

        wrapper.eq("name", "Jone");

        List<User> list = userMapper.selectList(wrapper);
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelectAllUser() {
        List<User> list = userMapper.selectList(null);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    void testInsertUser() {
        User user = new User();
        user.setName("Jone");
        user.setAge(11);
        user.setEmail("llll@163.com");
        userMapper.insert(user);
    }

    @Test
    void testUpdateUser() {

        User user = new User();
        user.setId(1210030660849299457L);
        user.setName("岳不群");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 测试乐观锁是否生效
     *
     * @Author lifp
     * @Date 2020/2/20
     */
    @Test
    void testVersion() {
        User user = userMapper.selectById(1230376151243857922L);

        user.setName("祖冲之aa");

        user.setVersion(1);

        userMapper.updateById(user);

    }

    /**
     * 测试分页插件
     *
     * @Author lifp
     * @Date 2020/2/20
     */
    @Test
    void testSelectByPage() {
        // 获取page对象，传递两个参数：当前页和每页记录数
        Page<User> page = new Page<>(1, 3);

        // 调用方法进行分页查询
        userMapper.selectPage(page, null);

        // 获取分页得到的数据
        List<User> list = page.getRecords();

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("总页数：" + page.getPages());
        System.out.println("每页数据条数：" + page.getSize());
        System.out.println("总数据条数：" + page.getTotal());
        System.out.println("上一页：" + page.hasPrevious());
        System.out.println("下一页：" + page.hasNext());
        System.out.println(list);
    }

    @Test
    void testDelete() {
        userMapper.deleteById(1230391382011625474L);

    }
}
