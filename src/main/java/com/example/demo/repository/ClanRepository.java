package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Clan;

@Repository
public interface ClanRepository extends JpaRepository<Clan, Integer>{

}
