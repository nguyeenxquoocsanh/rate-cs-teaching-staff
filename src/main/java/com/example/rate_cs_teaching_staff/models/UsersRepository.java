package com.example.rate_cs_teaching_staff.models;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UsersRepository extends JpaRepository<Staff, Integer>{
    List<Staff> findByRoleType(String roleType);
    List<Staff> findByClarityGreaterThanEqual(int clarity);
    List<Staff> findByNicenessGreaterThanEqual(int niceness);
    List<Staff> findByKnowledgeableScoreGreaterThanEqual(int knowledgeableScore);   
    // Custom query methods (if needed) can be defined here
}
