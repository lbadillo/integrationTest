package com.lbd.testliquibase.repository;


import com.lbd.testliquibase.entity.TutorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorialRepository extends JpaRepository<TutorialEntity, Integer> {
}
