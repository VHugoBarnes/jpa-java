package dev.victorvazquez;

import dev.victorvazquez.entity.Employee;
import dev.victorvazquez.util.UtilEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();

        List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        System.out.println("---------LIST ALL---------");
        employees.forEach(System.out::println);

        System.out.println("---------SEARCH ONE---------");
        int employeeId = 2;
        Employee employee = em.find(Employee.class, employeeId);
        System.out.println("Found employee "+employee);

//        System.out.println("---------CREATE ONE---------");
//        Employee newEmployee = new Employee();
//        newEmployee.setFirst_name("Nicole");
//        newEmployee.setPa_surname("Rodriguez");
//        newEmployee.setMa_surname("Gonzalez");
//        newEmployee.setEmail("nicole@gmail.com");
//        newEmployee.setSalary(1_000_000f);
//
//        em.getTransaction().begin();
//        em.persist(newEmployee);
//        em.getTransaction().commit();
//        System.out.println("New employee: " + newEmployee);

        System.out.println("---------UPDATE ONE---------");
        int employeeToUpdateId = 2;
        Employee employeeToUpdate = em.find(Employee.class, employeeToUpdateId);
        System.out.println("Employee to modify" + employeeToUpdate);

        employeeToUpdate.setFirst_name("Keko");
        employeeToUpdate.setPa_surname("Kaka");

        em.getTransaction().begin();
        em.merge(employeeToUpdate);
        em.getTransaction().commit();

        System.out.println("Updated employee"+employeeToUpdate);
    }
}