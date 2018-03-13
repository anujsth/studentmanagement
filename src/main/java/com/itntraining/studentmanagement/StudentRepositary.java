package com.itntraining.studentmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositary extends JpaRepository<Student, Long>{
	

}
