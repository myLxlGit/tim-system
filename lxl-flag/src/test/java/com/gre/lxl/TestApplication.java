package com.gre.lxl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gre.lxl.common.core.LoginEnum;
import com.gre.lxl.common.core.domain.LoginTeacher;
import com.gre.lxl.common.model.LoginUser;
import com.gre.lxl.common.util.PhoneUtils;
import com.gre.lxl.common.util.SecurityUtils;
import com.gre.lxl.common.util.StringUtils;
import com.gre.lxl.common.util.uuid.IdUtils;
import com.gre.lxl.domain.CsPrjEvaluationEx;
import com.gre.lxl.domain.Person;
import com.gre.lxl.domain.PersonT;
import com.gre.lxl.domain.RcTeacher;
import com.gre.lxl.framework.config.SecurityConfig;
import com.gre.lxl.framework.system.login.mapper.TestLoginMapper;
import com.gre.lxl.mapper.CsPrjEvaluationExMapper;
import com.gre.lxl.mapper.RcTeacherMapper;
import com.gre.lxl.system.dao.FairyAdminRepository;
import com.gre.lxl.system.dao.FairyCatRepository;
import com.gre.lxl.system.dto.FairyAdminDTO;
import com.gre.lxl.system.dto.User;
import com.gre.lxl.system.entity.FairyAdmin;
import com.gre.lxl.system.entity.FairyCat;
import com.gre.lxl.utils.GeneratePrjNo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author lxl
 * @date 2021/6/15 9:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LxlFlagApplication.class)
@Slf4j
public class TestApplication {

    @Autowired
    private FairyAdminRepository adminServe;
    @Autowired
    private FairyCatRepository fairyCatService;
    @Autowired
    private Person person;
    @Autowired
    private CsPrjEvaluationExMapper exMapper;
    @Autowired
    private RcTeacherMapper rcTeacherMapper;
    @Autowired
    private GeneratePrjNo generatePrjNo;

    @Test
    public void in() {
        User user = new User("1","小明");
        System.out.println(user);
    }

    @Test
    public void test55(){

        System.out.println(person.getName() +":" + person.getAge());

        System.out.println(PersonT.name +":" + PersonT.age);

    }

    @Test
    public void testPhone() {

        String phone = "19963414777";
        boolean mobile = PhoneUtils.isMobile(phone);
        System.out.println(mobile);
    }


    @Test
    public void saveTest() {
        FairyAdmin admin = new FairyAdmin();
        admin.setAdminNickname("yoko");
        admin.setAdminNickname("yokoNick");
        admin.setAdminPassword("123456");
        admin.setAdminNicpic("pic");
        FairyCat fairyCat = new FairyCat();
        fairyCat.setCatName("yoko_cat");
        FairyAdmin fairyAdmin = adminServe.save(admin);
        fairyCat.setTabId(fairyAdmin.getAdminId());
        fairyCatService.save(fairyCat);
    }

    @Test
    public void queryTest() {
        List<FairyAdminDTO> fairyAdminDTOS = adminServe.queryFairyAdminAll();
        System.out.println(fairyAdminDTOS);
        FairyAdminDTO fairyAdminDTO = fairyAdminDTOS.get(0);
        int i = adminServe.updateFairyAdminAllId(fairyAdminDTO);
        System.out.println(i);
    }

    @Test
    public void queryTestById() {
        int i = adminServe.updateFairyAdminById("8a8497877b77eafc017b77eb0e2c0000", "张三");
        if (i > 0) {
            Optional<FairyAdmin> Optional = adminServe.findById("8a8497877b77eafc017b77eb0e2c0000");
            if (Optional.isPresent()) {
                FairyAdmin fairyAdmin = Optional.get();
                System.out.println(fairyAdmin.getAdminId());
                System.out.println(fairyAdmin.getAdminNickname());
                System.out.println(fairyAdmin.getAdminNicpic());
                System.out.println(fairyAdmin.getAdminPassword());
                System.out.println(fairyAdmin.getAdminUsername());
            }
        }
    }

    @Test
    public void testException() {
        String str = "你好";
        System.out.println(str);
        try {
            str = null;
            int length = str.length();
            System.out.println(length);
        } catch (Exception e) {
            System.out.println("空指针异常");
        }
        System.out.println("产生异常之后的程序代码");
        System.out.println("6666");
    }

    @Test
    public void test333Exception() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    @Test
    public void save333Exception() {
        for (int i = 0; i < 20; i++) {
            CsPrjEvaluationEx csPrjEvaluationEx = new CsPrjEvaluationEx();
            csPrjEvaluationEx.setId(IdUtils.simpleUUID());
            csPrjEvaluationEx.setPrjName("内蒙夫测试"+i);
            csPrjEvaluationEx.setEvalState("0");
            csPrjEvaluationEx.setPrjNo(generatePrjNo.getPrjNo());
            csPrjEvaluationEx.setCreateUser("1");
            csPrjEvaluationEx.setCreateTime(new Date());
            exMapper.insert(csPrjEvaluationEx);
        }
    }

    @Test
    public void testStream() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }

    @Test
    public void testMaps() {
        List<String> list = new ArrayList<>();
        list.add("12");
        list.add("13");
        list.add("14");
        String name = "张三";
        AtomicInteger i = new AtomicInteger();
        List<Person> persons = list.stream().map(item -> {
            Person person = new Person();
            person.setAge(item);
            person.setName(name+ i.incrementAndGet());
            return person;
        }).collect(Collectors.toList());
        System.out.println(persons);
    }

    @Test
    public void testEnum() {
        LoginEnum loginEnum = LoginEnum.AA;
        System.out.println(loginEnum.getText());

        List<RcTeacher> rcTeachers = new ArrayList<>();
        for ( int i = 0; i < 3 ; i++) {
            RcTeacher rcTeacher = new RcTeacher();
            rcTeacher.setTeacherId(9+i+1);
            rcTeacher.setTeacherPassword("123456");
            rcTeacher.setTeacherDepartmentId(1);
            rcTeacher.setTeacherNumber("110"+(i+2)+"0008");
            rcTeacher.setTeacherName("测试老师"+i);
            rcTeachers.add(rcTeacher);
        }
//        int index = rcTeacherMapper.saveTeachers(rcTeachers);
//        System.out.println("index:"+ index);
        List<LoginTeacher> loginTeachers = JSONArray.parseArray(JSON.toJSONString(rcTeachers), LoginTeacher.class);
        System.out.println(loginTeachers);
        String string = loginTeachers.stream().map(LoginTeacher::getTeacherName).collect(Collectors.joining(",")).toString();
        System.out.println(string);

        StringBuilder builder = new StringBuilder();
        System.out.println(builder.toString());
    }

    @Autowired
    private TestLoginMapper testLoginMapper;

    @Test
    public void testCount() {
        int i = testLoginMapper.queryCount(1);
        System.out.println("i:"+i);
        List<RcTeacher> rcTeachers = new ArrayList<>();
        RcTeacher rcTeacher = new RcTeacher();
        rcTeacher.setTeacherId(14);
        rcTeacher.setTeacherPassword(SecurityUtils.encryptPassword("123456"));
        rcTeacher.setTeacherDepartmentId(1);
        rcTeacher.setTeacherNumber("11060008");
        rcTeacher.setTeacherName("测试老师4");
        rcTeachers.add(rcTeacher);
        int index = rcTeacherMapper.saveTeachers(rcTeachers);
        System.out.println("index:"+index);
    }

    @Test
    public void testIr() {
        List<RcTeacher> rcTeachers = rcTeacherMapper.selectList(null);
        //封装新的对象
        List<LoginUser> collect = rcTeachers.stream().map(item -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setLoginIp(String.valueOf(item.getTeacherId()));
            return loginUser;
        }).collect(Collectors.toList());
        //封装对象中某字符串拼接
        String collect1 = rcTeachers.stream().map(RcTeacher::getTeacherName).collect(Collectors.joining(","));
        //封装成map  key value
        Map<Integer, LoginUser> collect2 = rcTeachers.stream().collect(Collectors.toMap(RcTeacher::getTeacherId, rcTeacher -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setLoginIp(String.valueOf(rcTeacher.getTeacherId()));
            return loginUser;
        }));
        //根据名称正序
        List<RcTeacher> collect3 = rcTeachers.stream().sorted(Comparator.comparing(RcTeacher::getTeacherName)).collect(Collectors.toList());
        //根据名称倒序
        List<RcTeacher> collect4 = rcTeachers.stream().sorted(Comparator.comparing(RcTeacher::getTeacherName).reversed()).collect(Collectors.toList());
        //根据获取的某一字段正序
        List<String> collect5 = rcTeachers.stream().map(RcTeacher::getTeacherName).sorted(Comparator.comparing(Object::toString).reversed()).collect(Collectors.toList());
        //根据获取的某一字段倒序
        List<String> collect6 = rcTeachers.stream().map(RcTeacher::getTeacherName).sorted(Comparator.comparing(Object::toString)).collect(Collectors.toList());

    }

    @Test
    public void timeFormat() {
        Date date = new Date();
        System.out.println(date);
        //date 转localDateTime
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);
        //localDateTime转字符串
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        //字符串转LocalDateTime
        LocalDateTime parse = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);
        //localDateTime转date
        Date from = Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(from);
    }
}
