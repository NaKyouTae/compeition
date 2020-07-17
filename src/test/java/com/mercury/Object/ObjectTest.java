package com.mercury.Object;

import java.util.ArrayList;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercury.jpa.model.menu.Menu;
import com.mercury.jpa.model.user.User;
import com.mercury.util.ObjectUtil;
import com.mercury.util.UUIDUtil;
import com.mercury.vo.menu.MenuVO;

@SpringBootTest
public class ObjectTest {
	
	@Test
	@Order(1)
	void test() throws Exception {
		
		MenuVO vo = new MenuVO();
		vo.setIdx("test");
		vo.setParent("test");
		vo.setTitle("test");
		vo.setUrl("test");
		vo.setLevel(1);
		vo.setInsertDate("test");
		vo.setMenuGroup("test");
		vo.setMenuOrder(2);
		vo.setChild(Boolean.TRUE);
		vo.setChildren(new ArrayList<>());
		
		Menu menu = ObjectUtil.toObj(vo, new Menu());
		
		System.out.println(menu.toString());
	}
	
	@Test
	@Order(2)
	void mergeTest() throws Exception {
		
		User source = new User();
		
		source.setEmail("test");
		
		User target = new User();
		target.setEmail("aaa");
		target.setIdx(UUIDUtil.randomString());
		
		User user = ObjectUtil.toObj(source, target);
		System.out.println(user.toString());
	}
}
