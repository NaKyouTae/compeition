package com.competition.process.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.competition.jpa.model.menu.MyMenu;
import com.competition.jpa.repository.menu.MyMenuRepository;

@Component
@SuppressWarnings("unchecked")
public class MyMenuProcess {

	@Autowired
	private MyMenuRepository myMenuRepository;

	public <T extends Object> T seMyMenus() throws Exception {
		return (T) myMenuRepository.findAll(Sort.by(Sort.Direction.ASC, "menuOrder"));
	}
	public <T extends Object> T getListByLevelIsNull() throws Exception {
		return(T) myMenuRepository.findByParentIsNull();
	}
	public <T extends Object> T getListByLevel(String pidx) throws Exception {
		return(T) myMenuRepository.findByParent(pidx, Sort.by(Sort.Direction.ASC, "menuOrder"));
	}
	public <T extends Object> T seMyMenu(String idx) throws Exception {
		return (T) myMenuRepository.findByIdx(idx);
	}
	public <T extends Object> T inMyMenu(MyMenu dto) throws Exception {
		return (T) myMenuRepository.save(dto);
	}
	public <T extends Object> T upMyMenu(MyMenu dto) throws Exception {
		return (T) myMenuRepository.save(dto);
	}
	public void deMyMenu(MyMenu dto) throws Exception {
		myMenuRepository.delete(dto);
	}
	
}
