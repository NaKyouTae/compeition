package com.competition.jpa.repository.menu;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.competition.jpa.model.menu.MyMenu;

@Repository
public interface MyMenuRepository extends JpaRepository<MyMenu, Long> {
	MyMenu findByIdx(String idx);
	
	@Query(value="select idx, child, insertDate, level, menuGroup, menuOrder, parent, title, url, roleIdx, roleTitle from mymenu where parent = 'null' order by menuOrder asc", nativeQuery = true)
	List<MyMenu> findByParentIsNull();
	
	List<MyMenu> findByParent(String parent, Sort sort);
}


 