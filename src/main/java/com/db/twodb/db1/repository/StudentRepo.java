package com.db.twodb.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.db.twodb.db1.entity.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

	public Student findByName(String name);
	
//	@Query("SELECT * from Student s where s.address=:address")
//	public Student getStudentByAddress(@Param("address") String address);

}
