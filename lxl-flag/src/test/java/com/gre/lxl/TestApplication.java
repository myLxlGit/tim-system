package com.gre.lxl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gre.lxl.Atest.domain.AllDar;
import com.gre.lxl.Atest.domain.ChartParam;
import com.gre.lxl.Atest.domain.Lor;
import com.gre.lxl.Atest.domain.TrType;
import com.gre.lxl.Atest.service.IFtrService;
import com.gre.lxl.Atest.service.Zhujie;
import com.gre.lxl.common.core.LoginEnum;
import com.gre.lxl.common.core.domain.KvPair;
import com.gre.lxl.common.core.domain.LoginTeacher;
import com.gre.lxl.common.core.redis.RedisCache;
import com.gre.lxl.common.exception.CustomException;
import com.gre.lxl.common.model.LoginUser;
import com.gre.lxl.common.util.DateUtils;
import com.gre.lxl.common.util.PhoneUtils;
import com.gre.lxl.common.util.RestTemplateConfiguration;
import com.gre.lxl.common.util.SecurityUtils;
import com.gre.lxl.common.util.spring.SpringUtils;
import com.gre.lxl.common.util.uuid.IdUtils;
import com.gre.lxl.domain.*;
import com.gre.lxl.framework.system.login.mapper.TestLoginMapper;
import com.gre.lxl.functionIn.VUtils;
import com.gre.lxl.httpStudy.retrofit.remote.RemoteService;
import com.gre.lxl.httpStudy.retrofit.remote.rtService;
import com.gre.lxl.mapper.CsPrjEvaluationExMapper;
import com.gre.lxl.mapper.RcTeacherMapper;
import com.gre.lxl.service.QueryGrantTypeService;
import com.gre.lxl.system.dao.FairyAdminRepository;
import com.gre.lxl.system.dao.FairyCatRepository;
import com.gre.lxl.system.dto.FairyAdminDTO;
import com.gre.lxl.system.dto.User;
import com.gre.lxl.system.entity.FairyAdmin;
import com.gre.lxl.system.entity.FairyCat;
import com.gre.lxl.testCMB.request.*;
import com.gre.lxl.testCMB.response.*;
import com.gre.lxl.testCMB.serive.ICmbClientService;
import com.gre.lxl.testDelay.DelayTestService;
import com.gre.lxl.testTB.LoginUserV1;
import com.gre.lxl.testTB.PlanCon;
import com.gre.lxl.testTB.SpotM;
import com.gre.lxl.threadlocal.service.ServiceStart;
import com.gre.lxl.utils.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.gre.lxl.testCMB.common.FunCodeType.LC_ISSUE_INIT;
import static com.gre.lxl.testCMB.common.FunCodeType.SUCCESS;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.stream.Collectors.*;

/**
 * @author lxl
 * @date 2021/6/15 9:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LxlFlagApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestApplication {

    //    @Autowired
//    private FairyAdminRepository adminServe;
//    @Autowired
//    private FairyCatRepository fairyCatService;
    @Autowired
    private Person person;
    @Autowired
    private CsPrjEvaluationExMapper exMapper;
    @Autowired
    private RcTeacherMapper rcTeacherMapper;
    @Autowired
    private GeneratePrjNo generatePrjNo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void in() {
        User user = new User("1", "小明");
        System.out.println(user);
    }

    @Test
    public void test55() {

        System.out.println(person.getName() + ":" + person.getAge());

        System.out.println(PersonT.name + ":" + PersonT.age);

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
//        FairyAdmin fairyAdmin = adminServe.save(admin);
//        fairyCat.setTabId(fairyAdmin.getAdminId());
//        fairyCatService.save(fairyCat);
    }

    @Test
    public void queryTest() {
//        List<FairyAdminDTO> fairyAdminDTOS = adminServe.queryFairyAdminAll();
//        System.out.println(fairyAdminDTOS);
//        FairyAdminDTO fairyAdminDTO = fairyAdminDTOS.get(0);
//        int i = adminServe.updateFairyAdminAllId(fairyAdminDTO);
//        System.out.println(i);
    }

    @Test
    public void queryTestById() {
//        int i = adminServe.updateFairyAdminById("8a8497877b77eafc017b77eb0e2c0000", "张三");
//        if (i > 0) {
//            Optional<FairyAdmin> Optional = adminServe.findById("8a8497877b77eafc017b77eb0e2c0000");
//            if (Optional.isPresent()) {
//                FairyAdmin fairyAdmin = Optional.get();
//                System.out.println(fairyAdmin.getAdminId());
//                System.out.println(fairyAdmin.getAdminNickname());
//                System.out.println(fairyAdmin.getAdminNicpic());
//                System.out.println(fairyAdmin.getAdminPassword());
//                System.out.println(fairyAdmin.getAdminUsername());
//            }
//        }
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
//            System.out.println("空指针异常");
            e.printStackTrace();
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
            csPrjEvaluationEx.setPrjName("内蒙夫测试" + i);
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
            person.setName(name + i.incrementAndGet());
            return person;
        }).collect(toList());
        System.out.println(persons);
    }

    @Test
    public void testEnum() {
        LoginEnum loginEnum = LoginEnum.AA;
        System.out.println(loginEnum.getText());

        List<RcTeacher> rcTeachers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RcTeacher rcTeacher = new RcTeacher();
            rcTeacher.setTeacherId(9 + i + 1);
            rcTeacher.setTeacherPassword("123456");
            rcTeacher.setTeacherDepartmentId(1);
            rcTeacher.setTeacherNumber("110" + (i + 2) + "0008");
            rcTeacher.setTeacherName("测试老师" + i);
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
        System.out.println("i:" + i);
        List<RcTeacher> rcTeachers = new ArrayList<>();
        RcTeacher rcTeacher = new RcTeacher();
        rcTeacher.setTeacherId(14);
        rcTeacher.setTeacherPassword(SecurityUtils.encryptPassword("123456"));
        rcTeacher.setTeacherDepartmentId(1);
        rcTeacher.setTeacherNumber("11060008");
        rcTeacher.setTeacherName("测试老师4");
        rcTeachers.add(rcTeacher);
        int index = rcTeacherMapper.saveTeachers(rcTeachers);
        System.out.println("index:" + index);
    }

    @Test
    public void testIr() {
        List<RcTeacher> rcTeachers = rcTeacherMapper.selectList(null);
        //封装新的对象
        List<LoginUser> collect = rcTeachers.stream().map(item -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setLoginIp(String.valueOf(item.getTeacherId()));
            return loginUser;
        }).collect(toList());
        //封装对象中某字符串拼接
        String collect1 = rcTeachers.stream().map(RcTeacher::getTeacherName).collect(Collectors.joining(","));
        //封装成map  key value
        Map<Integer, LoginUser> collect2 = rcTeachers.stream().collect(Collectors.toMap(RcTeacher::getTeacherId, rcTeacher -> {
            LoginUser loginUser = new LoginUser();
            loginUser.setLoginIp(String.valueOf(rcTeacher.getTeacherId()));
            return loginUser;
        }));
        //根据名称正序
        List<RcTeacher> collect3 = rcTeachers.stream().sorted(Comparator.comparing(RcTeacher::getTeacherName)).collect(toList());
        //根据名称倒序
        List<RcTeacher> collect4 = rcTeachers.stream().sorted(Comparator.comparing(RcTeacher::getTeacherName).reversed()).collect(toList());
        //根据获取的某一字段正序
        List<String> collect5 = rcTeachers.stream().map(RcTeacher::getTeacherName).sorted(Comparator.comparing(Object::toString).reversed()).collect(toList());
        //根据获取的某一字段倒序
        List<String> collect6 = rcTeachers.stream().map(RcTeacher::getTeacherName).sorted(Comparator.comparing(Object::toString)).collect(toList());

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

    @Test
    public void queryEx() {
//        List<CsPrjEvaluationEx> prjEvaluationExes = exMapper.queryList("2021-11-01");
//
//        List<Date> collect = prjEvaluationExes.stream().map(CsPrjEvaluationEx::getCurrentTime).collect(Collectors.toList());
//        System.out.println(collect);

//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.DATE, -1);
//        date = calendar.getTime();
//        System.out.println(date);
//        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(format);

        float scale = (float) 1 / 2;
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(scale);
        float v = new BigDecimal(scale).setScale(2, 4).floatValue();
        float str = v * 100;
        String s = String.valueOf(str);
        int index = s.indexOf(".");
        s = s.substring(0, index);

        System.out.println("正确率：" + s + "%");

    }

    @Test
    public void parse() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(format);

        //生成今天的每日预测信息
        LocalDate parse = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date from = Date.from(parse.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(from);


        LocalDate localDate = LocalDate.now();
        Date newDate = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(newDate);
    }

    @Test
    public void testTY() {
        Date date = new Date();
        String dateFormat = getDateFormat(date, -1);
        String dateFormat0 = getDateFormat(date, 0);
        log.info("前一天：{}", dateFormat);
        log.info("当前天：{}", dateFormat0);


    }

    public String getDateFormat(Date date, Integer integer) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (integer == -1) {
            calendar.add(Calendar.DATE, -1);
        }
        date = calendar.getTime();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Test
    public void testAnyMatch() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(7);
        boolean b = list.stream().anyMatch(item -> item % 2 == 0);
        System.out.println(b);

        Optional<Integer> optional = list.stream().findFirst();
        if (optional.isPresent()) {
            Integer integer = optional.get();
            System.out.println(integer);
        }
        Calendar c1 = Calendar.getInstance();
        // 获得年份
        int year = c1.get(Calendar.YEAR);
        // 获得月份
        int month = c1.get(Calendar.MONTH) + 1;
        // 获得日期
        int date = c1.get(Calendar.DATE);
        // 获得小时
        int hour = c1.get(Calendar.HOUR_OF_DAY);
        // 获得分钟
        int minute = c1.get(Calendar.MINUTE);
        // 获得秒
        int second = c1.get(Calendar.SECOND);
        // 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
        int day = c1.get(Calendar.DAY_OF_WEEK);

        System.out.println(year);
        System.out.println(month);
        System.out.println(date);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        System.out.println(day);

        int x = 10;
        switch (x) {
            case 10:
                System.out.println("666");
                break;
            case 11:
                System.out.println("777");
                break;
            default:
        }
    }

    @Test
    public void testHttp() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:18080/app/system/mpi/queryMpiForecastDetailsByApp?dateToDay=2021-11-22")
                .method("GET", null)
                .addHeader("Authorization",
                        "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImFhZDgxZjZhZWZlMzRlMGJhMDZhNDc4ZjViZjM4MDIxIn0.t4B90Xdvgd-7bzVN-rOU-Hq58n70CmRyQSKDQUa-ghrU_KM4Zx1Hzs7-iUKm3mhmJ-_qyD9LvvIHopxWiyxqLA")
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();

            String read = read(body.byteStream());
            System.out.println(read);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHttpA() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n   \r\n    \"communication_url\":\"http://10.4.27.168:80/lcApplyResult/apply\",\r\n    \"corporate_no\":\"91310106B3333236XU\",\r\n    \"apply_no\":\"SUME20220100010\",\r\n    \"apply_type\":\"1\",\r\n    \"apply_result\":\"process\"\r\n    \r\n}");
        Request request = new Request.Builder()
                .url("bocomtest1.com/lcApplyResult/apply")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body1 = response.body();

            String read = read(body1.byteStream());
            System.out.println(read);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String read(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    @Test
    public void testBig() {
        BigDecimal rate = new BigDecimal(2).divide(new BigDecimal(3), 4, RoundingMode.HALF_UP)
                .setScale(3, RoundingMode.HALF_UP);
        System.out.println(rate);
        BigDecimal rate33 = new BigDecimal(2).divide(new BigDecimal(3), 4, RoundingMode.HALF_UP)
                .setScale(3, RoundingMode.UNNECESSARY);
        System.out.println(rate33);


        BigDecimal rate1 = new BigDecimal(2).divide(new BigDecimal(3), 2, RoundingMode.HALF_UP);
        System.out.println(rate1);

        BigDecimal bi = new BigDecimal(322);
        BigDecimal bi1 = new BigDecimal(333);
        BigDecimal subtract = bi.subtract(bi1);
        BigDecimal divide = subtract.divide(bi1, 4, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(new BigDecimal(100)).setScale(2, RoundingMode.UNNECESSARY);
        System.out.println("涨幅度：" + multiply + "%");


    }

    @Test
    public void testHtml() {
//        String html = "<p>\t电话：025 - 84531171 ；025 - 84531156</p><p>\t传真：025 - 86641172 ； 025 - 84402950</p><p>\t025 - 86640951 ； 025 - 84453553</p><p>\t地址：江苏省南京市长江路198号苏美达大厦9、10、11楼</p>";
//        String s = HtmlUtil.html2Text(html);
//        System.out.println(s);

        Calendar calendar = Calendar.getInstance();
        LocalDate now = LocalDate.now();
        Date from = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        calendar.setTime(from);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date time = calendar.getTime();
        LocalDateTime localDateTime = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("周：" + i);

//        LocalDate localDate = LocalDate.now(); //获取今天的日期
//        LocalDate laterDay = localDate.plusDays(+2); //前一天日期是今天减1
//        Date newDate = Date.from(laterDay.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        LocalDateTime localDateTime = newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(format);
    }

    @Test
    public void testH() {
        //判断今天是否是工作日 周末 还是节假日
        String year = "2022";
//        List<String> strings = HolidayUtil.holidayByList1(year);
//        List<String> collect = strings.stream().sorted().collect(Collectors.toList());
//        System.out.println(collect);
        Map<Date, DayStatusEnum> dateDayStatusEnumMap = HolidayUtil.holidayByList(year);
        System.out.println(dateDayStatusEnumMap);
    }


    @Test
    public void testDD() {
        String stringDate = getStringDate("0.1m");
        String stringDate1 = getStringDate("1m");
        System.out.println(stringDate);
        System.out.println(stringDate1);
    }

    private String getStringDate(String timeInterval) {
        Map<String, Integer> map = new HashMap<>();
        map.put("0.1m", -7);
        map.put("1m", -1);
        map.put("3m", -3);
        map.put("6m", -6);
        map.put("12m", -12);
        Integer integer = map.get(timeInterval);
        if (integer == null) {
            throw new CustomException("时间类型不存在");
        }
        Calendar calendar = Calendar.getInstance();
        if ("0.1m".equals(timeInterval)) {
            calendar.add(Calendar.DAY_OF_MONTH, integer);
        } else {
            calendar.add(Calendar.MONTH, integer);
        }
        Date date = calendar.getTime();
        return MpiUtils.dateToStringH(date);
    }

    @Test
    public void testA() {
//        List<String> nes = new ArrayList<>();
//        nes.add("1");
//        nes.add("1");
//        nes.add("1");
//        nes = nes.stream().distinct().collect(Collectors.toList());
//        boolean b = nes.stream().anyMatch("1"::equals);
//        boolean b2 = nes.stream().anyMatch("2"::equals);
//        System.out.println(b);
//        System.out.println(b2);
//        System.out.println(nes);

//        LocalDate now = LocalDate.now();
//        System.out.println(now);
//        Date from1 = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(from1);
//
//        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(format);
//        LocalDate parse = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        System.out.println(parse);
//        String text = "2021-12-08 10:07:15";
//        LocalDateTime parse1 = LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(parse1);
//        Date from = Date.from(parse1.atZone(ZoneId.systemDefault()).toInstant());
//        System.out.println(from);
//
//        LocalTime now1 = LocalTime.now();
//        System.out.println(now1);
//        String format1 = now1.format(DateTimeFormatter.ofPattern("HH:mm"));
//        System.out.println(format1);
//        LocalDateTime localDateTime = now1.atDate(now);
//        System.out.println(localDateTime);
        String timeStr = "2022-02-28T7:21:46.491Z";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeStr = timeStr.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = format.parse(timeStr);
            String format1 = defaultFormat.format(parse);
            Date parse1 = defaultFormat.parse(format1);
            System.out.println(parse1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Autowired
    private AsycService asycService;

    @Test
    public void testAA() throws InterruptedException {

        System.out.println("主线程 =====> 开始 =====> " + System.currentTimeMillis());
        asycService.sendMsg();
        Thread.sleep(2000);
        System.out.println("主线程 =====> 结束 =====> " + System.currentTimeMillis());

        Thread.sleep(4000); // 用于防止jvm停止，导致异步线程中断


//        List<Map<String, Object>> collect = new ArrayList<>();
//        Map<String, Object> mapI = new HashMap<>();
//        mapI.put("startTime", 1);
//        mapI.put("description", 1);
//        collect.add(mapI);
//        String router = "ReviewDetails,INCST,ReviewOverseas,FORCST";
//        String[] split = router.split(",");
//        for(Map<String, Object> map : collect) {
//            String startTime = map.get("startTime").toString();
//            String description = map.get("description").toString();
//        }
//
//
//        List<Map<String, Object>> collect1 = collect.stream().
//                filter(item -> (split[0].equals(item.get("router").toString()) && split[1].equals(item.get("supType").toString())) ||
//                        (split[2].equals(item.get("router").toString()) && split[3].equals(item.get("supType").toString()))
//                ).collect(Collectors.toList());
//        System.out.println(collect1);

    }

    @Autowired
    private RedisCache redisCache;

    @Test
    public void testres() {
        List<String> list = new ArrayList<>();
        list.add("sss");
        redisCache.setCacheMapValue("TestList", "sss", list);
        List<String> cacheMapValue = redisCache.getCacheMapValue("TestList", "ggg");
        System.out.println(cacheMapValue);
    }

    @Test
    public void testrr() {

        String text = "2021-12-08";
        LocalDate parse1 = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(parse1);
        Date from = Date.from(parse1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(from);


//        String str = "15.00";
//        BigDecimal bigDecimal = new BigDecimal(str);
//        System.out.println(bigDecimal);
//        BigDecimal bigDecimal1 = new BigDecimal(265);
//        String stt = null;
//        if (bigDecimal.compareTo(bigDecimal1) == 0) {
//            stt = "相等";
//        } else if (bigDecimal.compareTo(bigDecimal1) > 0) {
//            stt = "bigDecimal大";
//        } else {
//            stt = "bigDecimal小";
//        }
//        System.out.println(stt);


//        List<String> list = new ArrayList<>();
//        list.add("rrr");
//        list.add("ff");
//        list.add("fff");
//
//        AsyncManager.me().execute(AsyncFactory.recordOper(list));
//        AsyncManager.me().execute(AsyncFactory.recordOper1(list));


//        int threadCount = 3;
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
//
//        for (int i = 0; i < threadCount; i++) {
//            System.out.println("创建工作线程" + i);
//            Worker worker = new Worker(cyclicBarrier);
//            worker.start();
//        }
    }

    @Test
    public void testEnum1() {
        String text = "工作日";
        DataEnum of = DataEnum.of(text);
        assert of != null;
        String name = of.getName();
        System.out.println(name);
        DataEnum dataEnum = DataEnum.ofName(name);
        assert dataEnum != null;
        String text1 = dataEnum.getText();
        System.out.println(text1);

    }

    @Test
    public void testEnum11() {
//        String appId = "admin";
//        String sql = "SELECT USER_ID FROM SYS_USER WHERE USER_NAME=?";
//        String integer = jdbcTemplate.queryForObject(sql, new Object[]{appId}, String.class);
//        System.out.println(integer);
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, 1);
        Date time = instance.getTime();
        int season = DateUtils.getSeason(time);
        System.out.println(season);

    }


    public static final String APP_KEY_STR = "appKey";
    public static final String TIME_STAMP_STR = "timeStamp";
    public static final String SIGN = "sign";
    public static final String USER_AUTH_STR = "userAuth";
    public static final String COMPANY_AUTH_STR = "companyAuth";


    public static final String FDD_APP_KEY = "ITC";

    public static final String FDD_APP_SECRET = "sutx6hMhtyG1eTaIucqFJZ6fMihpfFoy";

    public static final String BASIC_URL = "https://msrv.sumec.com/dtx-api/contract";


    //创建合同


    //合同下载


    //自动签署
    @Test
    public void testEnum11222() {


//        public String accessAuthMethod(List<InnerParams> params, String url, String timestamp) {
//        contractNo
        String timestamp = System.currentTimeMillis() + "";
        List<InnerParams> params = new ArrayList<>();           //e647c0221b1e420bab0fe1f5d237e8cb
//        params.add(new InnerParams("contractUrl", "https://itradetest.sumec.com/erp/wx4/attach/appDown?attachId=e647c0221b1e420bab0fe1f5d237e8cb"));
        params.add(new InnerParams("contractUrl", "https://itradetest.sumec.com/erp/wx4/attach/i/e647c0221b1e420bab0fe1f5d237e8cb.pdf"));
//        params.add(new InnerParams("contractUrl", "http://10.4.100.115:18080/attach/appDown?attachId="));
        params.add(new InnerParams(APP_KEY_STR, FDD_APP_KEY));
        params.add(new InnerParams(TIME_STAMP_STR, timestamp));


        String collect = params.stream().sorted(InnerParams::compareTo).map(InnerParams::toString).collect(Collectors.joining());
        collect = collect + FDD_APP_SECRET;
        String sign = MD5.create().digestHex(collect).toUpperCase();
        System.out.println(sign);
        System.out.println(timestamp);
        Map<String, Object> map = params.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
        map.remove(APP_KEY_STR);
        map.remove(TIME_STAMP_STR);
        String body = HttpRequest.post(BASIC_URL + "/out/api/banksignauto")
                .form(map)
                .header(APP_KEY_STR, FDD_APP_KEY)
                .header(TIME_STAMP_STR, timestamp)
                .header(SIGN, sign).execute().body();
        System.out.println(body);
        //279bfebe1b8a49388022af80a5234265 //  8150f75f9c3149c992099ff37d41c380 //7aa5fde3733b4791a73dd79eb4837aac
//        }
    }

    //下载
    @Test
    public void testEnum112223() {
        String timestamp = System.currentTimeMillis() + "";
        List<InnerParams> params = new ArrayList<>();
        //7aa5fde3733b4791a73dd79eb4837aac
        params.add(new InnerParams("contractNo", "2c1352a6fb8a4939bc0eca8cfdc34876"));
        params.add(new InnerParams(APP_KEY_STR, FDD_APP_KEY));
        params.add(new InnerParams(TIME_STAMP_STR, timestamp));

        String collect = params.stream().sorted(InnerParams::compareTo).map(InnerParams::toString).collect(Collectors.joining());
        collect = collect + FDD_APP_SECRET;
        String sign = MD5.create().digestHex(collect).toUpperCase();
        System.out.println(sign);
        System.out.println(timestamp);
        Map<String, Object> map = params.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
        map.remove(APP_KEY_STR);
        map.remove(TIME_STAMP_STR);
        String body = HttpRequest.post(BASIC_URL + "/out/api/downloadcontract")
                .form(map)
                .header(APP_KEY_STR, FDD_APP_KEY)
                .header(TIME_STAMP_STR, timestamp)
                .header(SIGN, sign).execute().body();
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        int errCode = jsonObject.getInteger("errCode");
        if (errCode == 0) {
            //base64编码
            String result = jsonObject.getString("result");
            String newBase64 = "data:application/pdf;base64," + result;
            MultipartFile multipartFile = Base64DecodeMultipartFile.base64ToMultipartFile(newBase64);
            try {
                File file = MultipartFileToFile.multipartFileToFile(multipartFile);
                File fileDir = new File("D:\\bobom2");
                InputStream inputStream = null;
                OutputStream os = null;
                if (!fileDir.exists()) {
                    System.out.println("mkdir");
                    fileDir.mkdir();
                }
                try {
                    byte[] bs = new byte[1024 * 1024 * 5];//1k * 1024 * 5
                    int len;
                    inputStream = new FileInputStream(file);
                    os = new FileOutputStream(fileDir.getPath() + File.separator + file.getName());
                    while ((len = inputStream.read(bs)) != -1) {
                        os.write(bs, 0, len);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.close();
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                File sendFile = new File(fileDir.getPath() + File.separator + file.getName());
//                return sendFile;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testEnum141222() {
        VUtils.isTureOrFalse(true).trueOrFalseHandle(() -> {
            System.out.println("被你秀到了");
        }, () -> {
            System.out.println("秀不动了");
        });
        VUtils.isTureOrFalse(false).trueOrFalseHandle(() -> {
            System.out.println("被你秀到了");
        }, () -> {
        });
        long i = -1;
        int i1 = -1;
        System.out.println(i);

    }


    @Test
    public void testEnum14122243() {
        String str = getSHA256String("哈哈哈");
        System.out.println(str);
    }


    /**
     * 用java原生的摘要实现SHA256加密
     *
     * @param str 加密前的报文
     * @return
     */
    public static String getSHA256String(String str) {
        String encodeStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * byte[]转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte aByte : bytes) {
            String temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    //数字签名
    @Test
    public void teste() throws Exception {
        EncryptionUtil.generatePubPrivateKey();

        EncryptionUtil dig = new EncryptionUtil();

        String privatekeyStr = dig.getFileByFilePath("privatekey");
        String publickeyStr = dig.getFileByFilePath("publickey");


        String string = "哈哈哈";
        String sign = EncryptionUtil.sign(string, privatekeyStr);
        System.out.println("签名：");
        System.out.println(sign);
        System.out.println("----");
        boolean flag = EncryptionUtil.verify(sign, string, publickeyStr);
        System.out.println("验证签名结果:");
        System.out.println(flag);

    }

    @Autowired
    private ICmbClientService iCmbClientService;


    /**
     * 测试查询信用证
     */
    @Test
    public void teste444() {
        List<Onb2dcqlx1> list = new ArrayList<>();
        Onb2dcqlx1 onb2dcqlx1 = new Onb2dcqlx1();
        onb2dcqlx1.setIlcIdx("153FCD59D85B2001");
        list.add(onb2dcqlx1);
        List<Onb2dcqlz1> onb2dcqlz1s = iCmbClientService.queryLcDetails(list);
        log.info("查询信用证信息 = {}", onb2dcqlz1s);
    }

    /**
     * 测试模式列表查询
     */
    @Test
    public void teste4443() {
        Onb2dcmdx1 onb2dcmdx1 = new Onb2dcmdx1();
        //国际信用证
        onb2dcmdx1.setBuscod("N05020");
        List<Onb2dcmdx1> list = new ArrayList<>();
        list.add(onb2dcmdx1);
        List<Onb2dcmdz1> onb2dcmdz1 = iCmbClientService.queryLcBusMode(list);
        log.info("查询信用证信息 = {}", onb2dcmdz1);
    }

    /**
     * 测试查询协议编号列表
     */
    @Test
    public void teste4443g() {
        Onb2dcqtx1 onb2dcqtx1 = new Onb2dcqtx1();
        //深圳分行 业务机构根据账号开户行来的
//        onb2dcqtx1.setBrnnbr("755912");
        onb2dcqtx1.setCltnbr("7559360487");
        onb2dcqtx1.setBusmod("0000462161");
        onb2dcqtx1.setPatsub("00001");
        onb2dcqtx1.setBustyp("O");
        List<Onb2dcqtx1> list = new ArrayList<>();
        list.add(onb2dcqtx1);
        List<Onb2dcqtz1> onb2dcmdz1 = iCmbClientService.queryLcAgreementNumber(list);
        log.info("查询协议编号列表 = {}", onb2dcmdz1);


    }


    /**
     * 测试信用证经办
     */
    @Test
    public void testee() {
        List<Onbdcmstx1> tx1s = new ArrayList<>();
        Onbdcmstx1 tx1 = new Onbdcmstx1();
        tx1.setOprtyp("C");
        //业务模式
        tx1.setBusmod("0000462161");
        //业务子模式
        tx1.setPatsub("00001");

        tx1.setExtidx("20220530SUME00002");
        //客户号 银行提供
        tx1.setCltnbr("7559360487");
        //扣费账号  根据申请的招商来的结算户
        tx1.setDctacc("755936048710801");
        //1.
//        tx1.setLibrat("1");

        tx1.setCtanam("苏美达");
        tx1.setCtatel("18344445567");
        tx1.setTrsbrn("123456");
        //2.
//        tx1.setAppdes("");

        //使用全额保证金模式(FULL)的客户需线下与网点签署《国际信用证开证合作协议》，
        //提供协议编号查询，以供经办接口使用。
        //FULL,PART,NONE
        tx1.setDetmod("FULL");
        //3.协议编号
        tx1.setTexnbr("XYBH001");

        //无保证金模式下，根据客户号查询授信编号及风险额度编号信息。
        //4 授信编号
//        tx1.setCdtnbr("100XY2021001893");
        //5 额度编号
//        tx1.setRsknbr("EL2103300000194");

        tx1.setTrscod("121010"); //交易编码
        tx1.setFomtrs("N");
        tx1.setCfmins("Y");
        tx1.setCfmprt("BEN");
        tx1.setCfmrim("Y");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, +1);
        tx1.setExpdat(calendar.getTime());
        tx1.setPlaprt("sss");
        tx1.setAppadd("12");
        tx1.setBennam("12");
        tx1.setBenadd("adddd");
        tx1.setBencnr("CN");
        //6.
//        tx1.setBenacc("");
        //7.
//        tx1.setAdvbnk("");
        //8.
//        tx1.setAdvadd("");
        //9.
//        tx1.setAdvbk2("");
        //10.
//        tx1.setAdvad2("");

        tx1.setOpncur("10"); //Opncur  CNY
        tx1.setOpnamt(new BigDecimal("158"));
        //11.
//        tx1.setIcspct("");
        //12.
//        tx1.setDcspct("");

        tx1.setSpebnk("xxx");
        tx1.setPaytyp("NG");
        tx1.setPaydrt("ST");
        //13.
//        tx1.setDrtday("");
        //14.
//        tx1.setDrttyp("");

        tx1.setDraact("N");
        //15.
        tx1.setDrabnk(""); //受票行
        //16.
        tx1.setDrapct(""); //汇票占比 填写错误
        //17.
        tx1.setDraamt("123"); //汇票金额 填写错误

        tx1.setPtlshp("A");
        //18.
//        tx1.setPtldtl("");

        tx1.setTrstem("A");
        //20.
//        tx1.setTrsdtl("");
        //21.
//        tx1.setFrmadd("");
        //22.
//        tx1.setLodpot("");
        //23.
//        tx1.setDespot("");
        //24.
//        tx1.setFinadd("");
        //25.
//        tx1.setShpdat("");
        //26.
//        tx1.setShpped("");

        tx1.setReptyp("PWW");
        //27.
//        tx1.setPrsday("");
        //28.
//        tx1.setPrsunt("");

        tx1.setFeetem("fee");
        tx1s.add(tx1);

        List<Onbdcgodx1> dx1s = new ArrayList<>();
        Onbdcgodx1 dx1 = new Onbdcgodx1();
        dx1.setTemtyp("G");
        dx1.setTemtxt("123");
        Onbdcgodx1 dx2 = new Onbdcgodx1();
        dx2.setTemtyp("D");
        dx2.setTemtxt("123");
        dx1s.add(dx1);
        dx1s.add(dx2);

        List<Onbdcconx1> nx1s = new ArrayList<>();
        Onbdcconx1 nx1 = new Onbdcconx1();
        nx1.setConnbr("2016222050");
        nx1.setConccy("10");
        nx1.setConamt(new BigDecimal("50"));
        nx1s.add(nx1);

        LcIssueInitBodyReq lcIssueInitBodyReq = new LcIssueInitBodyReq();
        lcIssueInitBodyReq.setOnbdcmstx1(tx1s);
        lcIssueInitBodyReq.setOnbdcgodx1(dx1s);
        lcIssueInitBodyReq.setOnbdcconx1(nx1s);
        ObjectMapper objectMapper = new ObjectMapper();
        RootResp rootResp = iCmbClientService.LcIssueInit(lcIssueInitBodyReq);

        String resultCode = rootResp.getResponse().getHead().getResultcode();
        if (SUCCESS.equals(resultCode)) {
            try {
                String string = objectMapper.writeValueAsString(rootResp.getResponse().getBody());
                LcIssueInitBodyResp lcIssueInitBodyResp = objectMapper.readValue(string, LcIssueInitBodyResp.class);
                Onb2dcopz1 onb2dcopz1 = lcIssueInitBodyResp.getOnb2dcopz1().get(0);
                log.info("信用证经办返回信息 = {}", onb2dcopz1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.error("调用招商银行信用证经办接口失败,funCode = {}", LC_ISSUE_INIT);
            throw new CustomException(rootResp.getResponse().getHead().getResultmsg());
        }
    }


    private static final String URL = "https://tool.bitefu.net/jiari/?d={year}";

    @Test
    public void testRestTemple() {
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("year", "2022");
            ResponseEntity<String> exchange = new RestTemplate().getForEntity(URL, String.class, paramsMap);
//            RestTemplate rest = new RestTemplate();
//            URI uri = new URI(httpUrl);
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
//            HttpEntity<Object> objectHttpEntity = new HttpEntity<>(httpHeaders);
//
//            ResponseEntity<String> exchange = rest.exchange(uri, HttpMethod.GET, objectHttpEntity, String.class);
            log.info("响应信息 = {}", exchange);
            if (exchange.getStatusCode() == HttpStatus.OK) {
                String body = exchange.getBody();
                JSONObject jsonObject = JSONObject.parseObject(body);
                log.info("json = {}", jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private QueryGrantTypeService queryGrantTypeService;

    @Test
    public void testMapFun() {
        //测试mao + 函数式接口处理if else

        String ss = queryGrantTypeService.getResult("红包");
        log.info("结果 = {}", ss);

        String ss1 = "11";
        String ss2 = "10";
        boolean compare = ArithmeticUtils.compare(ss1, ss2);

        System.out.println(compare);

    }

    @Autowired
    private DelayTestService delayTestService;

    //测试有关  消息队列 实例
    @Test
    public void testDelay() {
        delayTestService.test();
    }


    //用户名
    private static final String userNameForTb = "shenqingrenzjj002@qq.com";
    //    private static final String userNameForTb = "yewuyuan001@qq.com";
    //密码
//    private static final String passwordForTb = "yewuyuan001";
    private static final String passwordForTb = "shenqingren002";
    //登录地址
//    private static final String login_url = "https://bamboo02.morningstone.cn:8443/api/v1/login";
    private static final String login_url = "http://39.106.184.170:7000/api/v1/login";
    //新建方案
//    private static final String insertApi_url = "https://bamboo02.morningstone.cn:8443/orderPlandetail/insertApi";
    private static final String insertApi_url = "http://39.106.184.170:7000/orderPlandetail/insertApi";
    //合同与方案建立关系
    private static final String buildConPrecept_url = "http://39.106.184.170:7000/orderSpotFuture/spotMatch";
    //新增购销合同接口
    private static final String contractInsert_Url = "http://39.106.184.170:7000/orderSpotPurchaseSellContract/insert";
    //获取期现项目中现货总利润 期货总利润 期现总利润接口
    private static final String queryTrader_url = "http://39.106.184.170:7000/orderSpotFuture/traderStatistics";
    //新增发票接口
    private static final String insertBill_url = "http://39.106.184.170:7000/OrderSellBillController/insert";
    //根据合同业务编号查找发票
    private static final String queryBill_url = "http://39.106.184.170:7000/OrderSellBillController/selectByContractId";
    //删除发票接口
    private static final String deleteBill_url = "http://39.106.184.170:7000/OrderSellBillController/delete";


    /**
     * 套保同步其他系统
     */
    @Test
    public void testTB() {
        String token = null;
        try {
//            LoginUserV1.LoginUserV1Builder builder = LoginUserV1.builder();
//            LoginUserV1 query = builder.name(userNameForTb)
//                    .password(passwordForTb).build();
//            String dataString = JSON.toJSONString(query);

//            String body = HttpRequest.post(login_url)
//                    .body(dataString, "application/json;charset=UTF-8")
//                    .execute().body();
//            log.info("返回信息:" + "       " + body);
//            JSONObject jsonObjectResp = JSONObject.parseObject(body);
//            int code = jsonObjectResp.getInteger("code");
//            if (code == HTTP_OK) {
//                String dataRespString = jsonObjectResp.getString("data");
//                JSONObject jsonObject = JSONObject.parseObject(dataRespString);
//                token = jsonObject.getString("token");
//            }

            PlanCon.PlanConBuilder query1 = PlanCon.builder();
            PlanCon cons = query1.planName("期货套保1")
                    .endTime(new Date())
                    .startTime(new Date())
                    .sname("001")
                    .futureProfitLossTop("0.1")
                    .spotProfitLossTop("0.3")
                    .profitLossTop("0.2")
                    .build();
//            ObjectMapper objectMapper = new ObjectMapper();
//            String string1 = objectMapper.writeValueAsString(cons);
            String string = JSON.toJSONString(cons);
            System.out.println("新建方案返回" + string);
//            String body1 = HttpRequest.post(insertApi_url)
//                    .body(string, "application/json;charset=UTF-8")
//                    .header("Authorization",token)
//                    .execute().body();
//            log.info("返回信息:" + "       " + body1);
//            JSONObject jsonObjectResp1 = JSONObject.parseObject(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testTBFrom() {

        //新建合同 新建方案 匹配合同和方案
        String token = "";
        String contractId = null;
        try {
            RestTemplate restTemplate = new RestTemplate(RestTemplateConfiguration.generateHttpRequestFactory());

            List<InnerParams> paramsLogin = new ArrayList<>();
            paramsLogin.add(new InnerParams("name", userNameForTb));
            paramsLogin.add(new InnerParams("password", passwordForTb));
            Map<String, Object> mapLogin = paramsLogin.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
            String stringLogin = JSON.toJSONString(mapLogin);

            URI uriuriLogin = new URI(login_url);
            HttpHeaders headersuriLogin = new HttpHeaders();
            headersuriLogin.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            HttpEntity<Object> tturiLogin = new HttpEntity<>(stringLogin, headersuriLogin);
            ResponseEntity<String> exchangeuriLogin = restTemplate.exchange(uriuriLogin, HttpMethod.POST, tturiLogin, String.class);
            String body = exchangeuriLogin.getBody();


//            String body = HttpRequest.post(login_url)
//                    .body(stringLogin,"application/json;charset=UTF-8")
//                    .execute().body();


            log.info("登录返回信息: {}", body);
            JSONObject jsonObjectResp = JSONObject.parseObject(body);
            int code = jsonObjectResp.getInteger("code");
            if (code == HTTP_OK) {
                String dataRespString = jsonObjectResp.getString("data");
                JSONObject jsonObject = JSONObject.parseObject(dataRespString);
                token = jsonObject.getString("token");
                log.info("登录返回token: {}", token);
            }
            //新增购销合同接口
            List<InnerParams> conParamsBody = new ArrayList<>();
            conParamsBody.add(new InnerParams("contractDocumentCode", "22SUMEC/FXC/XXZG/CG01")); //纸质合同编号
            conParamsBody.add(new InnerParams("commodityType", "大宗.矿产")); //商品种类
            conParamsBody.add(new InnerParams("unit", "吨")); //单位
            conParamsBody.add(new InnerParams("quantity", "5000")); //数量
            conParamsBody.add(new InnerParams("pricingMethod", "锁价")); //定价方式
            conParamsBody.add(new InnerParams("currencyType", "CNY")); //币种
            conParamsBody.add(new InnerParams("price", "1200")); //含税价格（原币）
            conParamsBody.add(new InnerParams("taxRate", "13"));    //税率
            conParamsBody.add(new InnerParams("exchangeMethod", "中行中间价")); //汇率方式
            conParamsBody.add(new InnerParams("exchangeRate", "1")); //汇率
            conParamsBody.add(new InnerParams("supplier", "天津市复兴昌国际贸易有限公司")); //供应商，采购合同填
            conParamsBody.add(new InnerParams("customer", "")); //客户，销售合同填
            conParamsBody.add(new InnerParams("purchaseSell", "0")); ////采购合同 数值为0、销售合同数值为1
            //新增字段
            conParamsBody.add(new InnerParams("signedDate", "2022-08-29  17:25:11"));    //签订日期
            conParamsBody.add(new InnerParams("bname", "业务员1"));    //商务人员
            conParamsBody.add(new InnerParams("remarks", "备注"));    //备注
            conParamsBody.add(new InnerParams("processStatus", "0"));    //审批状态  0:未提交 ，1：已通过 ,2：已撤销，3：已拒绝，4:待审批
            conParamsBody.add(new InnerParams("organization", "0"));     //机构，此处填机构code，参考数据字典
            conParamsBody.add(new InnerParams("sector", "1"));    //部门，此处填部门code，参考数据字典
            conParamsBody.add(new InnerParams("businessStaff", "293"));    //业务员，此处填员工code，参考数据字典
            conParamsBody.add(new InnerParams("executionStatus", "0"));    ///执行状态  0:未执行，1：执行中，2：已执行

            Map<String, Object> conMapBody = conParamsBody.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
            String conString = JSON.toJSONString(conMapBody);
            log.info("新增购销合同接口报文信息: {}", conString);

//            URI conuri = new URI(contractInsert_Url);
//            HttpHeaders headersCon = new HttpHeaders();
//            headersCon.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
//            headersCon.set("Authorization", token);
//            HttpEntity<Object> ttCon = new HttpEntity<>(conString, headersCon);
//            ResponseEntity<String> exchangeCon = restTemplate.exchange(conuri, HttpMethod.POST, ttCon, String.class);
//            String conBody = exchangeCon.getBody();
            String conBody = HttpRequest.post(contractInsert_Url)
                    .body(conString, "application/json;charset=UTF-8")
                    .header("Authorization", token)
                    .execute().body();

            log.info("新增购销合同接口返回信息: {}", conBody);
            JSONObject conresp = JSONObject.parseObject(conBody);
            String conresop = conresp.getString("data");
            JSONObject conData = JSONObject.parseObject(conresop);
            contractId = conData.getString("contractId");
            log.info("合同业务id: {}", contractId);


            //新建方案
            List<InnerParams> paramsBody = new ArrayList<>();
            paramsBody.add(new InnerParams("planName", "期货套保99"));
            paramsBody.add(new InnerParams("endTime", "2022-08-04 10:10:10"));
            paramsBody.add(new InnerParams("startTime", "2022-08-04 10:10:10"));
            paramsBody.add(new InnerParams("sname", "004"));
            paramsBody.add(new InnerParams("futureProfitLossTop", "0.5"));
            paramsBody.add(new InnerParams("spotProfitLossTop", "0.5"));
            paramsBody.add(new InnerParams("profitLossTop", "0.5"));
            Map<String, Object> mapBody = paramsBody.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
            String string = JSON.toJSONString(mapBody);
            log.info("新建方案接口报文信息: {}", string);


            URI uri = new URI(insertApi_url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);
            HttpEntity<Object> tt = new HttpEntity<>(string, headers);
            ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, tt, String.class);
            String body1 = exchange.getBody();
            log.info("新建方案接口返回信息: {}", body1);

//            String body1 = HttpRequest.post(insertApi_url)
//                    .body(string, "application/json;charset=UTF-8")
//                    .header("Authorization",token)
//                    .execute().body();
            JSONObject jsonObjectResp1 = JSONObject.parseObject(body1);

            Integer code1 = jsonObjectResp1.getInteger("code");
            if (code1 == HTTP_OK) {
                String data = jsonObjectResp1.getString("data");
                JSONObject jsonObject = JSONObject.parseObject(data);
                String planTimeId = jsonObject.getString("planTimeId");

                List<InnerParams> paramsBody2 = new ArrayList<>();
                paramsBody2.add(new InnerParams("contractId", contractId));
                paramsBody2.add(new InnerParams("matchedQuantity", "0"));
                paramsBody2.add(new InnerParams("planTimeId", planTimeId));
                paramsBody2.add(new InnerParams("purchaseSell", "0"));


                Map<String, Object> mapBody2 = paramsBody2.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
                String string2 = JSON.toJSONString(mapBody2);
                System.out.println("合同与方案建立关系报文：" + string2);

                URI uri2 = new URI(buildConPrecept_url);
                HttpHeaders headers2 = new HttpHeaders();
                headers2.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
                headers2.set("Authorization", token);
                HttpEntity<Object> tt2 = new HttpEntity<>(string2, headers2);
                ResponseEntity<String> exchange2 = restTemplate.exchange(uri2, HttpMethod.POST, tt2, String.class);
                String body12 = exchange2.getBody();
                System.out.println("合同与方案建立关系:" + body12);


            }
            //合同与方案建立关系  buildConPrecept_url


        } catch (Exception e) {
            e.printStackTrace();
        }
        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5ZXd1eXVhbjAwMUBxcS5jb20iLCJ1c2VyX2lkIjoyOTIsInBob25lIjoiMTUxMDAyMzAxOTMiLCJpZGVudGl0eSI6bnVsbCwic2NvcGUiOlsiUk9MRV9DT01NT05fQURNSU4iLCJST0xFX1BMQU5fQVBQUk9WRSIsIlJPTEVfU1BPVFNBTEUiLCJST0xFX1hJQU5IVU9ZRVdVWVVBTiIsIlJPTEVfVFJBREUiLCJST0xFX0JVU0lORVNTIl0sIm5hbWUiOiJ5ZXd1eXVhbjAwMUBxcS5jb20iLCJleHAiOjE2NTYwNjgyMzEsImlhdCI6MTY1NTk4MTgzMSwianRpIjoiYzFjMzFiOWYtODhiOC00ZTZmLTg2Y2UtZWI5NjVhYjcyMzRhIn0.-6EcWZqTYaP2XIYgOVYaNsFiLLvoZsjQJ01h6ojZIBo
        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5ZXd1eXVhbjAwMUBxcS5jb20iLCJ1c2VyX2lkIjoyOTIsInBob25lIjoiMTUxMDAyMzAxOTMiLCJpZGVudGl0eSI6bnVsbCwic2NvcGUiOlsiUk9MRV9DT01NT05fQURNSU4iLCJST0xFX1BMQU5fQVBQUk9WRSIsIlJPTEVfU1BPVFNBTEUiLCJST0xFX1hJQU5IVU9ZRVdVWVVBTiIsIlJPTEVfVFJBREUiLCJST0xFX0JVU0lORVNTIl0sIm5hbWUiOiJ5ZXd1eXVhbjAwMUBxcS5jb20iLCJleHAiOjE2NTYxNDg3MjIsImlhdCI6MTY1NjA2MjMyMiwianRpIjoiOGU2MDZhYzQtZTc0MC00OTFmLWE1NDktYTY4ZGU5ZTg1OWI4In0.sPbpHBUKlToDzDOPdjWahEvniuCY_BBg0gWgbx3l4j0

        //Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVucWluZ3JlbnpqajAwMkBxcS5jb20iLCJ1c2VyX2lkIjozMjIsInBob25lIjoiMTExMSIsImlkZW50aXR5IjpudWxsLCJzY29wZSI6WyJST0xFX0FETUlOIiwiUk9MRV9DT01NT05fQURNSU4iLCJST0xFX1NQT1QiLCJST0xFX1RSQURFIiwiMSIsIkFsbCIsInJpc2tfcm9sZSIsInByb21hbl9yb2xlIiwicHJvc3BvdF9yb2xlIiwienNoZnV0dXJlX3JvbGUiLCJqaWh1YSJdLCJuYW1lIjoic2hlbnFpbmdyZW56amowMDJAcXEuY29tIiwiZXhwIjoxNjU5Njc4Nzg0LCJpYXQiOjE2NTk1OTIzODQsImp0aSI6Ijg2NzIyMjkyLTdhZjEtNDkwMi05M2YxLTc0NmFlY2U2NzZhOSJ9.Fq1j1hUXhLnwABmdsr83Xxya3AT9N5d_6CyqKds40JM
    }

    //套保晨实，登录接口

    public String getMorningStoneToken() {
        String token = null;
        List<InnerParams> paramsLogin = new ArrayList<>();
        paramsLogin.add(new InnerParams("name", userNameForTb));
        paramsLogin.add(new InnerParams("password", passwordForTb));
        Map<String, Object> mapLogin = paramsLogin.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
        String stringLogin = JSON.toJSONString(mapLogin);

        String body = HttpRequest.post(login_url)
                .body(stringLogin, "application/json;charset=UTF-8")
                .execute().body();

        log.info("登录返回信息: {}", body);
        JSONObject jsonObjectResp = JSONObject.parseObject(body);
        int code = jsonObjectResp.getInteger("code");
        if (code == HTTP_OK) {
            String dataRespString = jsonObjectResp.getString("data");
            JSONObject jsonObject = JSONObject.parseObject(dataRespString);
            token = jsonObject.getString("token");
            log.info("登录返回token: {}", token);
        }
        return token;
    }


    @Test
    public void testYY() {
        String token = getMorningStoneToken();

        String planTimeId = "PL00005946";
        String url = queryTrader_url + "?planTimeId=" + planTimeId;
        String contr = HttpRequest.get(url)
                .contentType("application/json;charset=UTF-8")
                .header("Authorization", token)
                .execute().body();
        log.info("返回数据：{}", contr);
    }

    @Test
    public void testInsertBill() {
        String token = getMorningStoneToken();

        //新增发票接口
        //参数
        List<InnerParams> paramsLogin = new ArrayList<>();
        paramsLogin.add(new InnerParams("billNo", "102"));//发票编号
        paramsLogin.add(new InnerParams("serverName", "66"));
        paramsLogin.add(new InnerParams("billCode", "5655"));
        paramsLogin.add(new InnerParams("billNum", "测试"));
        paramsLogin.add(new InnerParams("kindName", "单位"));
        paramsLogin.add(new InnerParams("specification", "规格型号"));
        paramsLogin.add(new InnerParams("price", "100"));
        paramsLogin.add(new InnerParams("amunt", "100"));
        paramsLogin.add(new InnerParams("tax", "50"));
        paramsLogin.add(new InnerParams("taxRate", "50"));
        paramsLogin.add(new InnerParams("totalTax", "50"));
        paramsLogin.add(new InnerParams("money", "50"));
        paramsLogin.add(new InnerParams("type", "餐饮发票"));
        paramsLogin.add(new InnerParams("contractId", "CG00005850"));
        Map<String, Object> mapLogin = paramsLogin.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
        String billString = JSON.toJSONString(mapLogin);

        String backString = HttpRequest.post(insertBill_url)
                .body(billString, "application/json;charset=UTF-8")
                .header("Authorization", token)
                .execute().body();
        log.info("返回数据：{}", backString);
    }

    @Test
    public void testQueryBill() {
        //根据合同业务编号查找发票
        String token = getMorningStoneToken();
        String contractId = "CG00005850";

        StringBuilder sb = new StringBuilder();
        sb.append(queryBill_url).append("?");

        List<KvPair> list = new ArrayList<>();
        list.add(new KvPair("contractId", contractId));

        list.forEach(i -> sb.append(i.getKey()).append("=").append(i.getValue()).append("&"));
        sb.deleteCharAt(sb.length() - 1);

        String url = sb.toString();
        String contr = HttpRequest.get(url)
                .contentType("application/json;charset=UTF-8")
                .header("Authorization", token)
                .execute().body();
        log.info("返回数据：{}", contr);

        JSONObject jsonObjectResp = JSONObject.parseObject(contr);
        Integer code = jsonObjectResp.getInteger("code");
        String msg = jsonObjectResp.getString("msg");
        if (code == 200) {
            JSONArray data = jsonObjectResp.getJSONArray("data");
            List<BillResp> billResps = data.toJavaList(BillResp.class);
            log.info("转化返回数据：{}", billResps);
        } else {
            log.error("错误信息：{}", msg);
        }

    }

    @Test
    public void testDelBill() {
        //删除发票
        String token = getMorningStoneToken();
        //发票的记录id；根据合同业务编号查找发票接口返回值中的id字段（第6个接口）
        String id = "367";
        String url = deleteBill_url + "?id=" + id;
        String contr = HttpRequest.get(url)
                .contentType("application/json;charset=UTF-8")
                .header("Authorization", token)
                .execute().body();
        log.info("返回数据：{}", contr);
    }

    @Test
    public void testFF() {
        FileNoticeReq.FileNoticeReqBuilder builder = FileNoticeReq.builder();
        FileNoticeReq req = builder.tranCode("SMDFileToGj")
                .fileName("SUME_CIBS_lcApply109.zip")
                .srcPath("lcfile2/20220725")
                .build();
        String s = XmlUtils.convertToXml(req, FileNoticeReq.class);
        s = s.replace("GBK", "UTF_8");
        s = s.replace("__", "_");
        System.out.println("文件通知报文：" + s);

        try {
            RestTemplate restTemplate = new RestTemplate(RestTemplateConfiguration.generateHttpRequestFactory());
            URI uriuriLogin = new URI("https://202.108.57.89:33023");
            HttpHeaders headersuriLogin = new HttpHeaders();
            headersuriLogin.setContentType(org.springframework.http.MediaType.APPLICATION_ATOM_XML);
            HttpEntity<Object> tturiLogin = new HttpEntity<>(s, headersuriLogin);
            ResponseEntity<String> exchangeuriLogin = restTemplate.exchange(uriuriLogin, HttpMethod.POST, tturiLogin, String.class);
            String body = exchangeuriLogin.getBody();
            log.info("返回数据： {}", body);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testTr() {
//        String ss = "{\"contractDocumentCode\":\"22SUMEC/FXC/XXZG/CG01\",\"commodityType\":\"大宗.矿产\",\"unit\":\"吨\",\"quantity\":\"5000\",\"pricingMethod\":\"锁价\",\"currencyType\":\"CNY\",\"price\":\"1200\",\"taxRate\":\"13\",\"exchangeMethod\":\"中行中间价\",\"exchangeRate\":\"1\",\"supplier\":\"天津市复兴昌国际贸易有限公司\",\"customer\":\"\",\"purchaseSel\":\"0\"}\n";
        StringBuilder builder = new StringBuilder();
        builder.append("555");
        builder.append(System.lineSeparator());
        builder.append("555");
        System.out.println("打印：" + builder.toString());

        String text = "555da" + "\n"
                + "666";
        System.out.println("打印2：" + text);


    }


    @Test
    public void testBuild() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGVucWluZ3JlbnpqajAwMkBxcS5jb20iLCJ1c2VyX2lkIjozMjIsInBob25lIjoiMTExMSIsImlkZW50aXR5IjpudWxsLCJzY29wZSI6WyJST0xFX0FETUlOIiwiUk9MRV9DT01NT05fQURNSU4iLCJST0xFX1NQT1QiLCJST0xFX1RSQURFIiwiMSIsIkFsbCIsInJpc2tfcm9sZSIsInByb21hbl9yb2xlIiwicHJvc3BvdF9yb2xlIiwienNoZnV0dXJlX3JvbGUiLCJqaWh1YSJdLCJuYW1lIjoic2hlbnFpbmdyZW56amowMDJAcXEuY29tIiwiZXhwIjoxNjU5Njc5MzQwLCJpYXQiOjE2NTk1OTI5NDAsImp0aSI6IjczOGUzZjdiLWEyMzYtNDk5MC1iYmY1LWFhMWEwOWQzYjZlZSJ9.1LuAQlBUD9rBDtqhmFcPGtiMvVTwPQD2SHAbcGTKNYg";
        List<InnerParams> paramsBody2 = new ArrayList<>();
        paramsBody2.add(new InnerParams("contractId", "1234"));
        paramsBody2.add(new InnerParams("matchedQuantity", "1"));
        paramsBody2.add(new InnerParams("planTimeId", "PL00005890"));
        paramsBody2.add(new InnerParams("purchaseSell", "0"));

        SpotM spotM = new SpotM();
        spotM.setContractId("1234");
        spotM.setMatchedQuantity(1);
        spotM.setPlanTimeId("PL00005890");
        spotM.setPurchaseSell("0");

        try {
            RestTemplate restTemplate = new RestTemplate(RestTemplateConfiguration.generateHttpRequestFactory());

//            Map<String, Object> mapBody2 = paramsBody2.stream().collect(Collectors.toMap(InnerParams::getKey, InnerParams::getValue));
//            String string2 = JSON.toJSONString(mapBody2);
            String string2 = JSON.toJSONString(spotM);
            System.out.println("报文：" + string2);


            URI uri2 = new URI(buildConPrecept_url);
            HttpHeaders headers2 = new HttpHeaders();
            headers2.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            headers2.set("Authorization", token);
            HttpEntity<Object> tt2 = new HttpEntity<>(string2, headers2);
            ResponseEntity<String> exchange2 = restTemplate.exchange(uri2, HttpMethod.POST, tt2, String.class);
            String body12 = exchange2.getBody();
            System.out.println("合同与方案建立关系:" + body12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //合同与方案建立关系:{"code":504,"msg":"匹配数量不能为空或者匹配数量大于合同剩余数量","data":"匹配失败"}
    }

    @Test
    public void testHttpRequest() {
        MsgType[] values = MsgType.values();
//        for (MsgType msgType : MsgType.values()) {
//            System.out.println(msgType.getName());
//            System.out.println(msgType.getValue());
//            System.out.println(msgType.getType());
//        }
        String ofterConvert = LpConfirmTypeEnum.getOfterConvert("1");
        System.out.println("转化后" + ofterConvert);

        try {
            String oldDateStr = "2023-07-13T00:00:00+08:00";
            //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
            Date date = df.parse(oldDateStr);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            Date date1 = df1.parse(date.toString());
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //  Date date3 =  df2.parse(date1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    @SuppressWarnings("all") //忽略警告
    public void testII() {
        try {
            String str = "dtr";
            IFtrService bean = (IFtrService) SpringUtils.getBean(TrType.getClazz(str));
            List<Lor> list = bean.testList();
            if (!CollectionUtils.isEmpty(list)) {
                List<AllDar> collect = list.stream().map(AllDar::new).collect(toList());
                System.out.println(collect);
            } else {
                System.out.println("空户籍");
            }
            List<AllDar> list3 = new ArrayList<>();
            Arrays.stream(TrType.values()).forEach(item -> {
                IFtrService bean2 = (IFtrService) SpringUtils.getBean(item.getClazz());
                List<Lor> list1 = bean2.testList();
                List<AllDar> collect = list1.stream().map(AllDar::new).collect(toList());
                list3.addAll(collect);
            });
            System.out.println(list3);

//            DataEnum.ofExist("5");

            Map<String, Object> map = new HashMap<>();
            map.put("dd", "dd");
            if ("dtr".equals(str)) {
                map.put("dd", "dtr");
            }
            System.out.println(map);


//            ChartParam chartParam = new ChartParam();
//            chartParam.setTimeInterval("year1");
//
//            System.out.println(chartParam);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //定时timer
    @Test
    @Zhujie(company = "hha", personId = 13)
    public void D() {
        //判断节假日
        Date date = new Date();
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(dayOfWeek == DayOfWeek.SATURDAY);
        System.out.println(dayOfWeek == DayOfWeek.TUESDAY);
    }

    @Resource
    private RemoteService remoteService;

    @Test
    public void rrr() {
        Map<String, String> map = new HashMap<>();
        map.put("uscc", "91340521551817498G");
        map.put("telephone", "0555-6827987");
        map.put("bankName", "武安市农行");
        map.put("address", "安徽省马鞍山市当涂经济开发区");
        map.put("bankAccount", "1561601021000194038");

        String test11 = remoteService.getTest11(map);
        System.out.println(test11);
    }

    @Test
    public void D1() {

//        String test = remoteService.getTest();
//        System.out.println(test);

        SgDTO dto = new SgDTO();
        dto.setId("1");
        dto.setNo("123");
        dto.setAmt(new BigDecimal("1"));
        dto.setQty(new BigDecimal("1"));

        SgDTO dto1 = new SgDTO();
        dto1.setId("2");
        dto1.setNo("123");
        dto1.setAmt(new BigDecimal("4"));
        dto1.setQty(new BigDecimal("4"));

        SgDTO dto2 = new SgDTO();
        dto2.setId("2");
        dto2.setNo("1234");
        dto2.setAmt(new BigDecimal("4"));
        dto2.setQty(new BigDecimal("4"));

        List<SgDTO> list = new ArrayList<>();
        list.add(dto);
        list.add(dto1);
        list.add(dto2);

        Map<String, Map<String, Object>> collect = list.stream()
                .collect(groupingBy(SgDTO::getNo, collectingAndThen(toList(), m -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("count", (long) m.size());
                    //对分组的list求和
                    map.put("amtC", m.stream().map(SgDTO::getAmt).reduce(BigDecimal.ZERO, BigDecimal::add));
                    map.put("qtyC", m.stream().map(SgDTO::getQty).reduce(BigDecimal.ZERO, BigDecimal::add));
                    return map;
                })));
//        Map<String, List<Person>> collect = list.stream().collect(groupingBy(Person::getName));
//        Map<String,Long> map = list.stream().collect(groupingBy(Person::getName,  Collectors.summingLong(Person::getAge)));
        System.out.println(collect);


        Map<String, Object> map = MapUtils.buildMap("jja", "jaja");


    }


    @Test
    public void ihdsaih() {
        //输出固定长度16位，不足向左补齐
        String originalString = "Hello World"; // Replace "Hello World" with your original string
        int fixedLength = 16;
        String paddedString = String.format("%" + fixedLength + "s", originalString);
        System.out.println(paddedString);

        //输出固定长度16位，不足向右补齐
        String originalString1 = "你好世界"; // Replace "你好世界" with your original string
        int fixedLength1 = 16;
        String paddedString1 = String.format("%-" + fixedLength1 + "s", originalString1);
        System.out.println(paddedString1);
    }

    @Test
    public void sms() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId("accessKeyId")
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret("accessKeySecret");
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";

        com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);


        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，
        // 建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
//        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("ACCESS_KEY_ID", "ACCESS_KEY_SECRET");
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("XL应用")
                .setTemplateCode("SMS_460790165")
                .setPhoneNumbers("15064102036")
                .setTemplateParam("{\"code\":\"1234\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
        com.aliyun.teaconsole.Client.log(com.aliyun.teautil.Common.toJSONString(resp));

    }

    @Test
    public void testtt() {
        //java隐藏手机号 和 身份证
        String phone = "15064102036";
        phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println("隐藏手机号 = " + phone);

        //身份证
        String idCard = "371323199703308711";
        idCard = idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
        System.out.println("隐藏身份证 = " + idCard);
    }


    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testSuo() throws InterruptedException {

        //8.1. 可重入锁（Reentrant Lock）
        //基于Redis的Redisson分布式可重入锁RLock Java对象实现了java.util.concurrent.locks.Lock接口。同时还提供了异步（Async）、反射式（Reactive）和RxJava2标准的接口。

        //RLock lock = redisson.getLock("anyLock");
        // 最常见的使用方法
        //lock.lock();
        //大家都知道，如果负责储存这个分布式锁的Redisson节点宕机以后，而且这个锁正好处于锁住的状态时，这个锁会出现锁死的状态。
        // 为了避免这种情况的发生，Redisson内部提供了一个监控锁的看门狗，
        // 它的作用是在Redisson实例被关闭前，不断的延长锁的有效期。
        // 默认情况下，看门狗的检查锁的超时时间是30秒钟，也可以通过修改Config.lockWatchdogTimeout来另行指定。

        //另外Redisson还通过加锁的方法提供了leaseTime的参数来指定加锁的时间。超过这个时间后锁便自动解开了。
        String redisFix = "redis01";

        RLock lock = redissonClient.getLock(redisFix);
        //尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean isLock = lock.tryLock(5, 5, TimeUnit.MINUTES);

        if (isLock) {
            System.out.println("上锁");
        } else {
            System.out.println("系统繁忙！ 存在上锁");
        }

    }

    @Resource
    private rtService rtService;

    @Test
    public void tesstsss() {
//        String s = rtService.queryValidArInvBillListByOmCon(10177);
//        String s1 = rtService.querySalesActuralIoList(109978);

//        System.out.println("queryValidArInvBillListByOmCon = " + s);
//        System.out.println("querySalesActuralIoList = " + s1);


        BigDecimal bigDecimal = new BigDecimal("-15.55");

        String string = bigDecimal.toString();
        System.out.println(string);

    }

    @Autowired
    ServiceStart serviceStart;

    @Test
    public void testlocal() {
        serviceStart.start();
    }



}
