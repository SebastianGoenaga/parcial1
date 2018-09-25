package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import org.junit.Test;

import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {
		EmployeeGenerator generador = new EmployeeGenerator();
		qt().forAll(generador.empleados()).check((e) -> {
			ErrorType error = validator.validate(e).orElse(ErrorType.INVALID_UNKNOWN_AFFILIATION);
			if (e.getSocialSecurityType() == SocialSecurityType.SISBEN
					&& e.getSalary() >= 1500) {
				return error == ErrorType.INVALID_SISBEN_AFFILIATION;
			}
			else if (e.getSocialSecurityType() == SocialSecurityType.EPS
					&& e.getSalary() >= 10000) {
				return error == ErrorType.INVALID_EPS_AFFILIATION;
			}
			else if (e.getSocialSecurityType() == SocialSecurityType.PREPAID
					&& e.getSalary() < 10000) {
				return error == ErrorType.INVALID_PREPAID_AFFILIATION;
			}
			else {
				return true;
			}
		});
	}
}
