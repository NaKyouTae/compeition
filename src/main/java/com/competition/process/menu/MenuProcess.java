package com.competition.process.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.competition.dto.menu.MenuDto;
import com.competition.jpa.model.Menu;
import com.competition.jpa.repository.MenuRepository;

@Component
@SuppressWarnings("unchecked")
public class MenuProcess {

	@Autowired
	private MenuRepository menuRepository;

	public <T extends Object> T getList() throws Exception {
		List<MenuDto> result = new ArrayList<>();

		List<Menu> list = menuRepository.findAll(Sort.by(Sort.Direction.ASC, "level"));
		
		for (Menu l : list) {

			if(!l.getParent().equals("")) continue;
			
			MenuDto dto = new MenuDto();

			dto.setIdx(l.getIdx());
			dto.setInsert_date(l.getInsert_date());
			dto.setParent(l.getParent());
			dto.setTitle(l.getTitle());
			dto.setUrl(l.getUrl());
			dto.setGroup(l.getGroup());
			dto.setOrder(l.getOrder());
			dto.setLevel(l.getLevel());
			dto.setChild(l.getChild());
			dto.setChildren(menuRepository.findByParent(l.getIdx()));
			result.add(dto);

		}

		return (T) result;
	}

	public <T extends Object> T inMenu(Menu dto) throws Exception {
		return (T) menuRepository.save(dto);
	}

	public <T extends Object> T upMenu(Menu dto) throws Exception {
		return (T) menuRepository.save(dto);
	}

	public void deMenu(Menu dto) throws Exception {
		menuRepository.delete(dto);
	}
	
}
