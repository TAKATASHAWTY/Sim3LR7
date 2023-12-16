package edu.Sim3LR7.dao;

import edu.Sim3LR7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long>{
}
