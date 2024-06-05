package com.recipe.sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.sharing.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
