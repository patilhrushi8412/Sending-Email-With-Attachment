package com.te.mail.studentservice;

import static com.te.mail.studentconstants.StudentConstants.DELETING_STUDENT_DETAILS_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.GETTING_ALL_STUDENT_DETAILS_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.GETTING_STUDENT_DETAILS_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT;
import static com.te.mail.studentconstants.StudentConstants.STUDENT_ADDED_SUCCESFULLY;
import static com.te.mail.studentconstants.StudentConstants.STUDENT_IS_NOT_PRESENT_ON_THIS_ID;
import static com.te.mail.studentconstants.StudentConstants.STUDENT_IS_NOT_PRESENT_WHICH_YOU_WANT_TO_UPDATE;
import static com.te.mail.studentconstants.StudentConstants.STUDENT_WHICH_YOU_WANT_TO_ADD_IS_ALLREADY_PRESENT;
import static com.te.mail.studentconstants.StudentConstants.UPDATE_STUDENT_DETAILS_SUCCESFULLY;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.mail.EmailbyattechmentApplication;
import com.te.mail.studentcustomexception.StudentCustomException;
import com.te.mail.studentdto.StudentDto;
import com.te.mail.studententity.Student;
import com.te.mail.studentrepository.StudRepo;
import com.te.mail.studentrepository.StudentRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo repo;

	@Autowired
	private StudRepo repo1;

	@Autowired
	private EmailbyattechmentApplication application;

	@Override
	public Student addStudent(StudentDto studentDto) {
		try {
			Optional<Student> findByStudent = repo.findByStudentEmail(studentDto.getStudentEmail());
			if (findByStudent.isPresent()) {
				log.error(STUDENT_WHICH_YOU_WANT_TO_ADD_IS_ALLREADY_PRESENT);
				throw new StudentCustomException(STUDENT_WHICH_YOU_WANT_TO_ADD_IS_ALLREADY_PRESENT);
			} else {
				Student student = new Student();
				BeanUtils.copyProperties(studentDto, student);
				log.info(STUDENT_ADDED_SUCCESFULLY);
				try {
				application.mail(student);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				Student save = repo.save(student);
				return save;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Student getStudent(int id) {
		try {
			Optional<Student> student = repo.findById(id);
			if (!student.isPresent()) {
				log.error(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
				throw new StudentCustomException(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
			} else {
				log.info(GETTING_STUDENT_DETAILS_SUCCESFULLY);
				return student.get();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteStudent(int id) {
		try {
			Optional<Student> student = repo.findById(id);
			if (!student.isPresent()) {
				log.error(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
				throw new StudentCustomException(STUDENT_IS_NOT_PRESENT_ON_THIS_ID);
			} else {
				log.info(DELETING_STUDENT_DETAILS_SUCCESFULLY);
				repo.delete(student.get());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> getAllStudent() {
		try {
			List<Student> studentList = repo.findAll();
			if (studentList.isEmpty()) {
				log.error(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
				throw new StudentCustomException(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
			} else {
				log.info(GETTING_ALL_STUDENT_DETAILS_SUCCESFULLY);
				return studentList;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Student updateStudent(StudentDto studentDto) {
		try {
			Optional<Student> student = repo.findByStudentEmail(studentDto.getStudentEmail());
			if (!student.isPresent()) {
				log.error(STUDENT_IS_NOT_PRESENT_WHICH_YOU_WANT_TO_UPDATE);
				throw new StudentCustomException(STUDENT_IS_NOT_PRESENT_WHICH_YOU_WANT_TO_UPDATE);
			} else {
				BeanUtils.copyProperties(studentDto, student.get());
				Student save = repo.save(student.get());
				log.info(UPDATE_STUDENT_DETAILS_SUCCESFULLY);
				return save;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> getAllByStudentId(String schoolId) {
		try {
			List<Student> students = repo.findBySchoolId(schoolId);
			if (students.isEmpty()) {
				log.error(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
				throw new StudentCustomException(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
			} else {
				return students;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> getStudentByStandard(int standard) {
		try {
			List<Student> students = (List<Student>) repo1.findByStandard(standard);
//			System.out.println(students.get(1).getSchoolId());
			if (students.isEmpty()) {
				throw new StudentCustomException(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
			} else {
				return students;
//				if (stud.isEmpty()) {
//					throw new StudentCustomException(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
//				} else {
//					return stud;
//				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Student> getStudentsByAnyWord(String str) {
		try {
			List<Student> students = repo
					.findAllByStudentFirstNameContainingIgnoreCaseOrStudentLastNameContainingIgnoreCaseOrStudentEmailContainingIgnoreCaseOrStudentAddressContainingIgnoreCaseOrSchoolIdContainingIgnoreCase(
							str, str, str, str, str);
			if (students.isEmpty()) {
				throw new StudentCustomException(NO_ONE_STUDENT_IS_NOT_PRESENT_AT_THE_MOMENT);
			} else {
				return students;
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
