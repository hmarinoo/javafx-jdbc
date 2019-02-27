package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	public List<Department> findAll(){
		List<Department> list = new ArrayList<>();
		list.add(new Department(1,"Dep1"));
		list.add(new Department(2,"Dep2"));
		list.add(new Department(3,"Dep3"));
		list.add(new Department(4,"Dep4"));
			
		return list;
	}
}
