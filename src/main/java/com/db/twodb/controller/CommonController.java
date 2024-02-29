package com.db.twodb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.twodb.db1.entity.Student;
import com.db.twodb.db1.repository.StudentRepo;
import com.db.twodb.db2.entity.Book;
import com.db.twodb.db2.repository.BookRepo;

@RestController
@RequestMapping("/common")
public class CommonController {
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	@Value("${spring.profiles.active}")
	 private String profile;
	
	@Autowired
	private StudentRepo sRepo;
	@Autowired
	private BookRepo  bRepo;
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		log.info("Student Details::"+student.toString());
		return sRepo.save(student);
	}
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book) {
		log.info("Student Details::"+book.toString());
		return bRepo.save(book);
	}
@GetMapping("/getStudent")
	public List<Student> getAllStudent() {
		log.info("Getting All Student");
		return sRepo.findAll();
	}
@GetMapping("/getProfile")
	private String getProfile() {
		return profile;
	}
@GetMapping("/getStudent/{sid}")
public Student getStudentById(@PathVariable Integer sid ) {
	
	return sRepo.findById(sid).get();
}

@GetMapping("/getStudentByName")
public Student getStudentByName(@RequestParam String name ) {
	
	return sRepo.findByName(name);
}
@GetMapping("/getBookByName")
public Book getBookByName(@RequestParam String name) {
	log.info("Inside getBookByName");
	return bRepo.getBookByName(name);
}


}
