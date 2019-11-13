package com.example.my_springboot_demo;

import com.example.my_springboot_demo.designer_model.builder_model.ComputerB;
import com.example.my_springboot_demo.domain.UserVo;
import com.example.my_springboot_demo.redis.RedisKeyUtil;
import com.example.my_springboot_demo.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.sun.javafx.css.SizeUnits.EX;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringbootDemoApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Resource
	private ValueOperations<String,Object> valueOperations;

	@Autowired
	private HashOperations<String, String, Object> hashOperations;

	@Autowired
	private ListOperations<String, Object> listOperations;

	@Autowired
	private SetOperations<String, Object> setOperations;

	@Autowired
	private ZSetOperations<String, Object> zSetOperations;

	@Resource
	private RedisService redisService;

	@Test
	public void contextLoads() {
	}


	@Test
	public void testObj() throws Exception{
		UserVo userVo = new UserVo();
		userVo.setAddress("上海");
		userVo.setName("测试dfas");
		userVo.setAge(123);
		ValueOperations<String,Object> operations = redisTemplate.opsForValue();
		redisService.expireKey("name",20, TimeUnit.SECONDS);
		String key = RedisKeyUtil.getKey(UserVo.Table,"name",userVo.getName());
		operations.set(key,userVo);
		UserVo vo = (UserVo) operations.get(key);
		System.out.println("key:" + key + "->" + vo);
	}

	@Test
	public void testValueOption() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setAddress("上海");
		userVo.setName("jantent");
		userVo.setAge(23);
		valueOperations.set("test",userVo);

		System.out.println(valueOperations.get("test"));
	}

	@Test
	public void testSetOperation() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setAddress("北京");
		userVo.setName("jantent");
		userVo.setAge(23);

		ComputerB computerB = new ComputerB.ComputerBuilder("主板","cpu","hd","电源","显卡").setMouse("鼠标").build() ;
		ComputerB computerB1 = new ComputerB.ComputerBuilder("主板", "cpu", "hd", "power", "显卡").build();

		setOperations.add("computerB:test", computerB, computerB1);
		Set<Object> result = setOperations.members("user:test");
		System.out.println(result);
	}

	@Test
	public void testRedisDistributedLockJ() {
		Boolean result = redisService.tryLock("dlock:test-try-lock", "a", 100, TimeUnit.SECONDS);
		Boolean result2 = redisService.tryLock("dlock:test-try-lock", "a", 100, TimeUnit.SECONDS);
		System.out.println("=-=--===============");
		System.out.println(result + ", " + result2);

	}


}
