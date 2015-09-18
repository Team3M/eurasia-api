package com.threem.eurasia.sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.OptionalDouble;

public class SampleTest {

	public void sampleTest() {
		Optional<Long> value = someMethod();
	
		if (value.isPresent()) {
			Long val = value.get();
		}
		
	}

	private Optional<Long> someMethod() {
		return null;
	}
	
	public void sampleStreamBefore() {
		
		int sum = 0;
		int count = 0;
		
		Collection<Employee> emps = new ArrayList<Employee>();
		
		Employee emp1 = new Employee("Bob", 100);
		Employee emp2 = new Employee("Tom", 200);
		
		emps.add(emp1);
		emps.add(emp2);
		
		for (Employee employee : emps) {
			if (employee.getSalary() > 100) {
				sum += employee.getSalary();
			}
		}

		double avg = (double)sum/ count;
		System.out.println(avg);
		
	}
	
	public void sampleStreamAfter() {
		
		Collection<Employee> emps = new ArrayList<Employee>();
		
		Employee emp1 = new Employee("Bob", 100);
		Employee emp2 = new Employee("Tom", 200);
		
		emps.add(emp1);
		emps.add(emp2);

		OptionalDouble avgOpt = emps.stream()
					.filter(x -> x.getSalary() > 100)
					.mapToInt(x -> x.getSalary())
					.average();
		
		double avg = avgOpt.getAsDouble();
		
		System.out.println(avg);
		
	}
	
	
}
