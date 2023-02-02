package com.te.mail.studententity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Data;

@Data
@Entity
@NamedStoredProcedureQueries(@NamedStoredProcedureQuery(name = "procedure", procedureName = "getStudByStandard", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "standard", type = Integer.class) }))	
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	private String studentFirstName;
	private String studentLastName;
	private int studentStandard;
	private String studentEmail;
	private String studentAddress;
	private String schoolId;
}
