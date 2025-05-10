
package com.demo.firstSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.firstSpring.Entity.Login;

public interface LoginDao extends JpaRepository<Login , Long>{

}
