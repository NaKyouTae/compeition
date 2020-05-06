package com.competition.Object;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.competition.jpa.model.menu.Menu;
import com.competition.util.ObjectUtil;
import com.competition.vo.menu.MenuVO;

@SpringBootTest
public class ObjectTest {
	
	@Test
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
		
		Menu menu = ObjectUtil.toObject(vo, new Menu());
		
		System.out.println(menu.toString());
	}
}
