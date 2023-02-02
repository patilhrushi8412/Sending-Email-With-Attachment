package com.te.mail.studentcontroller;

import static com.te.mail.studentconstants.StudentConstants.ADDING_STUDENT;
import static com.te.mail.studentconstants.StudentConstants.DELETE_STUDENT_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.DELETING_STUDENT_DETAILS;
import static com.te.mail.studentconstants.StudentConstants.GETTING_ALL_DETAILS;
import static com.te.mail.studentconstants.StudentConstants.GETTING_ALL_STUDENT_DETAILS_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.GETTING_STUDENT_DETAILS;
import static com.te.mail.studentconstants.StudentConstants.GETTING_STUDENT_DETAIL_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.STUDENT_ADDED_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.STUDENT_UPDATED_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.UPDATING_STUDENT_DETAILS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.mail.studentdto.StudentDto;
import com.te.mail.studententity.Student;
import com.te.mail.studentresponse.StudentResponse;
import com.te.mail.studentservice.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/add")
	public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentDto studentDto) {
		log.info(ADDING_STUDENT);
		Student student = service.addStudent(studentDto);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, STUDENT_ADDED_SUCCESFULLY, student),
				HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<StudentResponse> getStudent(@PathVariable int id) {
		log.info(GETTING_STUDENT_DETAILS + id);
		Student student = service.getStudent(id);
		return new ResponseEntity<StudentResponse>(
				new StudentResponse(false, GETTING_STUDENT_DETAIL_SUCCESFULLY, student), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id) {
		log.info(DELETING_STUDENT_DETAILS + id);
		service.deleteStudent(id);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, DELETE_STUDENT_SUCCESFULLY, null),
				HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<StudentResponse> getAllStudent() {
		log.info(GETTING_ALL_DETAILS);
		List<Student> student = service.getAllStudent();
		return new ResponseEntity<StudentResponse>(
				new StudentResponse(false, GETTING_ALL_STUDENT_DETAILS_SUCCESFULLY, student), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentDto studentDto) {
		log.info(UPDATING_STUDENT_DETAILS);
		Student student = service.updateStudent(studentDto);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, STUDENT_UPDATED_SUCCESFULLY, student),
				HttpStatus.OK);
	}

	@GetMapping("/getAllBySchoolId/{schoolId}")
	public ResponseEntity<StudentResponse> getAllBySchoolId(@PathVariable String schoolId) {
		log.info("Getting Students By School Id");
		List<Student> student = service.getAllByStudentId(schoolId);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, "Getting Students By School Id", student),
				HttpStatus.OK);
	}

	@GetMapping("/getStandard/{standard}")
	public ResponseEntity<StudentResponse> getStudentListWithstandard(@PathVariable int standard) {
		List<Student> student = service.getStudentByStandard(standard);
		return new ResponseEntity<StudentResponse>(
				new StudentResponse(false, "Getting Students Of " + standard + " Standard", student), HttpStatus.OK);
	}

	@GetMapping("/getStudentsByAnyWord/{str}")
	public ResponseEntity<StudentResponse> getStudentsByAnyWord(@PathVariable String str) {
		log.info("Getting Students By Any word");
		List<Student> student = service.getStudentsByAnyWord(str);
		return new ResponseEntity<StudentResponse>(new StudentResponse(false, "Getting Students", student),
				HttpStatus.OK);
	}
}
