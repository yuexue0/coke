package com.xuelin.coke;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xuelin.coke.domain.fastjson.Column;
import com.xuelin.coke.domain.fastjson.Query;
import com.xuelin.coke.domain.fastjson.User;
import com.xuelin.coke.domain.fastjson.UserGroup;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.collections4.map.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FastjsonTest {

    /*
    public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
    public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
    public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean
    public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
    public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合
    public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
    public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
    public static final Object toJSON(Object javaObject); //将JavaBean转换为JSONObject或者JSONArray。
     */

    @Test
    public void jsonTest01(){
        long  millis = 1594138987429L;
        Date date = new  Date(millis);
        System.out.println(JSON.toJSONString(date));

        String s = JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(s);
    }

    /**
     * java对象转 json字符串
     *
     * 结果：
     * 简单java类转json字符串:{"password":"123456","username":"dmego"}
     * List<Object>转json字符串:[{"password":"123123","username":"zhangsan"},{"password":"321321","username":"lisi"}]
     * 复杂java类转json字符串:{"name":"userGroup","users":[{"password":"123123","username":"zhangsan"},{"password":"321321","username":"lisi"}]}
     */
    @Test
    public void objectTOJson(){
        //简单java类转json字符串
        User user = new User("dmego", "123456");
        String UserJson = JSON.toJSONString(user);
        System.out.println("简单java类转json字符串:"+UserJson);

        //List<Object>转json字符串
        User user1 = new User("zhangsan", "123123");
        User user2 = new User("lisi", "321321");
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        String ListUserJson = JSON.toJSONString(users);
        System.out.println("List<Object>转json字符串:"+ListUserJson);

        //复杂java类转json字符串
        UserGroup userGroup = new UserGroup("userGroup", users);
        String userGroupJson = JSON.toJSONString(userGroup);
        System.out.println("复杂java类转json字符串:"+userGroupJson);
    }

    /**
     * json字符串转java对象
     * 注：字符串中使用双引号需要转义 (" --> \"),这里使用的是单引号
     *
     * 结果：
     * json字符串转简单java对象:User [username=dmego, password=123456]
     * json字符串转List<Object>对象:[User [username=zhangsan, password=123123], User [username=lisi, password=321321]]
     * json字符串转复杂java对象:UserGroup [name=userGroup, users=[User [username=zhangsan, password=123123], User [username=lisi, password=321321]]]
     */
    @Test
    public void JsonTOObject(){
        /* json字符串转简单java对象
         * 字符串：{"password":"123456","username":"dmego"}*/

        String jsonStr1 = "{'password':'123456','username':'dmego'}";
        User user = JSON.parseObject(jsonStr1, User.class);
        System.out.println("json字符串转简单java对象:"+user.toString());

        /*
         * json字符串转List<Object>对象
         * 字符串：[{"password":"123123","username":"zhangsan"},{"password":"321321","username":"lisi"}]
         */
        String jsonStr2 = "[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]";
        List<User> users = JSON.parseArray(jsonStr2, User.class);
        System.out.println("json字符串转List<Object>对象:"+users.toString());

        /*json字符串转复杂java对象
         * 字符串：{"name":"userGroup","users":[{"password":"123123","username":"zhangsan"},{"password":"321321","username":"lisi"}]}
         * */
        String jsonStr3 = "{'name':'userGroup','users':[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]}";
        UserGroup userGroup = JSON.parseObject(jsonStr3, UserGroup.class);
        System.out.println("json字符串转复杂java对象:"+userGroup);
    }


    /**
     * 读取类路径下的配置文件
     * 解析成对象数组并返回
     * @throws IOException
     *
     */
    @Test
    public void test() throws IOException {
        // 读取类路径下的query.json文件
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("upload/fastdemo.json");
        String jsontext = IOUtils.toString(inputStream,"UTF-8");
        // [{"id":"user_list","key":"id","tableName":"用户列表","className":"cn.dmego.domain.User","column":[{"key":"rowIndex","header":"序号","width":"50","allowSort":"false"},{"key":"id","header":"id","hidden":"true"},{"key":"name","header":"姓名","width":"100","allowSort":"true"}]},{"id":"role_list","key":"id","tableName":"角色列表","className":"cn.dmego.domain.Role","column":[{"key":"rowIndex","header":"序号","width":"50","allowSort":"false"},{"key":"id","header":"id","hidden":"true"},{"key":"name","header":"名称","width":"100","allowSort":"true"}]}]
        System.out.println(jsontext);
        // 先将字符jie串转为List数组
        List<Query> queryList = JSON.parseArray(jsontext, Query.class);
        for (Query query : queryList) {
            List<Column> columnList = new ArrayList<Column>();
            List<LinkedMap<String,Object>> columns = query.getColumn();
            for (LinkedMap<String, Object> linkedMap : columns) {
                //将map转化为java实体类
                Column column = (Column)map2Object(linkedMap, Column.class);
                /*
                Column{key='rowIndex', header='序号', width='50', allowSort=false, hidden=null}
                Column{key='id', header='id', width='null', allowSort=null, hidden=true}
                Column{key='name', header='姓名', width='100', allowSort=true, hidden=null}
                Column{key='rowIndex', header='序号', width='50', allowSort=false, hidden=null}
                Column{key='id', header='id', width='null', allowSort=null, hidden=true}
                Column{key='name', header='名称', width='100', allowSort=true, hidden=null}
                 */
                System.out.println(column.toString());
                columnList.add(column);
            }
            query.setColumnList(columnList); //为columnList属性赋值
        }
        // [Query{id='user_list', key='null', tableName='null', className='null', column=[{key=rowIndex, header=序号, width=50, allowSort=false}, {key=id, header=id, hidden=true}, {key=name, header=姓名, width=100, allowSort=true}], columnList=[Column{key='rowIndex', header='序号', width='50', allowSort=false, hidden=null}, Column{key='id', header='id', width='null', allowSort=null, hidden=true}, Column{key='name', header='姓名', width='100', allowSort=true, hidden=null}]}, Query{id='role_list', key='null', tableName='null', className='null', column=[{key=rowIndex, header=序号, width=50, allowSort=false}, {key=id, header=id, hidden=true}, {key=name, header=名称, width=100, allowSort=true}], columnList=[Column{key='rowIndex', header='序号', width='50', allowSort=false, hidden=null}, Column{key='id', header='id', width='null', allowSort=null, hidden=true}, Column{key='name', header='名称', width='100', allowSort=true, hidden=null}]}]
        System.out.println(queryList.toString());
    }

    /**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            // 用到了反射
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String flag = (String) map.get(field.getName());
                if(flag != null){
                    if(flag.equals("false") || flag.equals("true")){
                        field.set(obj, Boolean.parseBoolean(flag));
                    }else{
                        field.set(obj, map.get(field.getName()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
