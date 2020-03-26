package com.competition.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.competition.dto.menu.MenuDto;
import com.competition.jpa.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	MenuDto findByIdx(String idx);
	
	@Query(value="select idx, child, insert_date, level, menu_group, menu_order, parent, title, url from menu where parent is null order by menu_order asc", nativeQuery = true)
	List<Menu> findByParentIsNull();
	
	List<Menu> findByParent(String parent, Sort sort);
}


 