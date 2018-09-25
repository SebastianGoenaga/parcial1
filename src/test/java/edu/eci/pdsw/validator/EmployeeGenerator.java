package edu.eci.pdsw.validator;

import org.quicktheories.core.*;
import static org.quicktheories.generators.SourceDSL.*;
import org.quicktheories.generators.Generate;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;


public class EmployeeGenerator {

	public Gen<Employee> empleados() {
		return id().zip(salario(), socialT(), (id, salario, socialT) -> new Employee(id, salario, socialT));
	}

	private Gen<Integer> id() {
		return integers().between(1000, 100000);
	}

	private Gen<Long> salario() {
		return longs().between(100, 50000);
	}

	private Gen<SocialSecurityType> socialT() {
		return Generate.enumValues(SocialSecurityType.class);
	}

}
