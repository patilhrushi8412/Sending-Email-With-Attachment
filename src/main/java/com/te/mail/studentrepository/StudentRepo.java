package com.te.mail.studentrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.mail.studententity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Optional<Student> findByStudentEmail(String studentEmail);

	List<Student> findBySchoolId(String schoolId);

	List<Student> findAllByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrStudentEmailContainingIgnoreCaseOrStudentAddressContainingIgnoreCaseOrSchoolIdContainingIgnoreCase(
			String str, String str2, String str3, String str4, String str5);

	List<Student> findByStudentIdIn(List<Integer> id);

}
