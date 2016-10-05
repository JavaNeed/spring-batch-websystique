package javabeat.net;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EmployeeDataMapper implements FieldSetMapper<Employee>{

	@Override
	public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
		Employee employee = new Employee();
		employee.setEmpId(fieldSet.readString(0));
		employee.setCity(fieldSet.readString(1));
		employee.setCountry(fieldSet.readString(2));
		return employee;
	}
}
