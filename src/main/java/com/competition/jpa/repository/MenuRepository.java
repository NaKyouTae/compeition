package com.competition.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.competition.dto.menu.MenuDto;
import com.competition.jpa.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	MenuDto findByIdx(String idx);
}
