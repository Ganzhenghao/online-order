package com.dfyang.onlineorder.dao;

import com.dfyang.onlineorder.entity.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndentDao extends JpaRepository<Indent, Integer> {
}
