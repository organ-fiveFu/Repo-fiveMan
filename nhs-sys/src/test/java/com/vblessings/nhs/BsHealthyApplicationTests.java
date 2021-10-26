package com.vblessings.nhs;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.component.RedisCache;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.ftp.service.IFtpService;
import com.vblessings.nhs.jwt.JWTInfo;
import com.vblessings.nhs.jwt.jwtHelper;
import com.vblessings.nhs.mapper.LoginMapper;
import com.vblessings.nhs.model.entity.sys.SysEmployeeInfo;
import com.vblessings.nhs.model.entity.sys.SysLogin;
import com.vblessings.nhs.model.po.business.QueryVitalSignPO;
import com.vblessings.nhs.model.vo.business.BusMenuSelectVO;
import com.vblessings.nhs.model.vo.nurse.VitalSignRecordVO;
import com.vblessings.nhs.service.BusVitalSignRecordService;
import com.vblessings.nhs.service.Impl.MenuServiceImpl;
import com.vblessings.nhs.service.LoginService;
import com.vblessings.nhs.util.AESUtill;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BsHealthyApplication.class})
@ActiveProfiles("dev")
class BsHealthyApplicationTests {

    //加密密钥

    private String priKeyPath="adewdaw";

    //过期时间

    private int expire=86400;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private LoginService loginService;

    @Resource
    private IFtpService iFtpService;

    @Resource
    RedisCache redisCache;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private BusVitalSignRecordService busVitalSignRecordService;

    @Test
    public void play(){
        System.out.println(snowflakeComponent.getInstance().nextId());
    }

    @Test
    public void paly1(){
        System.out.println(AESUtill.encryptData("123esfre34efresw"));
        System.out.println(AESUtill.deciTwo("Iq4jyR+440Blhh9ahOwuNjHiJgzgez/xel3LYQhQhr8="));
    }

    @Test
    public void paly2() throws Exception {
        SysEmployeeInfo sysEmployeeInfo = new SysEmployeeInfo();
        sysEmployeeInfo.setEmployeeCode("123456");
        sysEmployeeInfo.setPassword("123456");
        sysEmployeeInfo.setName("张三");
        sysEmployeeInfo.setId(snowflakeComponent.getInstance().nextId());
        String  token = jwtHelper.createToken(new JWTInfo(sysEmployeeInfo.getEmployeeCode(), sysEmployeeInfo.getId() , sysEmployeeInfo.getName()));
        System.out.println(token);
    }
    @Test
    public void paly3(){
        System.out.println(redisCache.getCacheObject("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiY2MiLCJleHAiOjE2MjM3NDA3MjYsInVzZXJJZCI6MTMxLCJlbXBsb3llZV9jb2RlIjoiMjAyMSJ9.6PYOol3owH6Aq93SBJHU9d5WxQ0pIG1ywHxYLHgdQY8").toString());
    }

    /**
     * 分页测试
     */
    @Test
    public void paly4(){
        Example example = new Example(SysLogin.class);
        Example.Criteria C = example.createCriteria();
        C.andEqualTo("employeeCode","word");
        // 分页参数
        PageHelper.startPage(1, 10);

        List<SysLogin> sysLogins = loginMapper.selectByExample(example);
        //获取分页数据
        PageInfo pageInfo = new PageInfo(sysLogins);

        // 获取列表总数
        System.out.println(pageInfo);
    }

    @Test
    public void play5(){
        QueryVitalSignPO queryVitalSignPO = new QueryVitalSignPO();
        queryVitalSignPO.setBusinessNo("202109052217");
        queryVitalSignPO.setStartTime("2021-09-06");
        queryVitalSignPO.setEndTime("2021-09-10");
        VitalSignRecordVO vitalSignRecordVO = busVitalSignRecordService.queryVitalSignRecord(queryVitalSignPO);
        System.out.println(vitalSignRecordVO);
    }

    /**
     * 获取时间列表测试
     */
    @Test
    public void handleCirculationDate(){
//        MenuServiceImpl menuService = new MenuServiceImpl();
//        List<BusMenuSelectVO> busMenuSelectVOS = new ArrayList<>();
//        BusMenuSelectVO busMenuSelectVO1 = new BusMenuSelectVO();
//        BusMenuSelectVO busMenuSelectVO2 = new BusMenuSelectVO();
//        BusMenuSelectVO busMenuSelectVO3 = new BusMenuSelectVO();
//        busMenuSelectVO1.setId(1L);
//        busMenuSelectVO1.setDate("2021-08-06");
//        busMenuSelectVO2.setId(2L);
//        busMenuSelectVO2.setDate("2021-08-07");
//        busMenuSelectVO3.setId(3L);
//        busMenuSelectVO3.setDate("2021-08-09");
//        busMenuSelectVOS.add(busMenuSelectVO1);
//        busMenuSelectVOS.add(busMenuSelectVO2);
//        busMenuSelectVOS.add(busMenuSelectVO3);
//        List<BusMenuSelectVO> result = menuService.handleCirculationDate(new Date(1628179200000L), new Date(1628697600000L), busMenuSelectVOS);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date start = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date end = calendar.getTime();
        System.out.println("");
    }
}
