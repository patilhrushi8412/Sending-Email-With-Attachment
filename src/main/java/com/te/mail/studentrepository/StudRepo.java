package com.te.mail.studentrepository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.te.mail.studententity.Student;

@Repository
public class StudRepo {

	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Student> findByStandard(int standard) {
		return em.createNamedStoredProcedureQuery("procedure").setParameter("standard", standard)
				.getResultList();
	}
}
