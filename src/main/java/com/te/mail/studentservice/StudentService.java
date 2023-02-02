package com.te.mail.studentservice;

import java.util.List;

import com.te.mail.studentdto.StudentDto;
import com.te.mail.studententity.Student;

public interface StudentService {

	Student addStudent(StudentDto studentDto);

	Student getStudent(int id);

	void deleteStudent(int id);

	List<Student> getAllStudent();

	Student updateStudent(StudentDto studentDto);

	List<Student> getAllByStudentId(String schoolId);

	List<Student> getStudentByStandard(int standard);

	List<Student> getStudentsByAnyWord(String str);

}
