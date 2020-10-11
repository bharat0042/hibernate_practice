package com.practice.eight;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.practice.eight.entity.Employee;

public class PracticeEightDemo {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			//Create 
			Employee emp = new Employee("Mark", "Zcrberg", "Flipkart");
			session.beginTransaction();
			session.save(emp);
			session.getTransaction().commit();
			
			//Read
			session = factory.getCurrentSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Employee> list = session.createQuery("from Employee e where e.firstName like 'Z%'").getResultList();
			System.out.println("\n\n");
			for(Employee temp : list) {
				System.out.println(temp);
			}
			session.getTransaction().commit();
			
			//Update
			session = factory.getCurrentSession();
			session.beginTransaction();
			int numOfUpdatedItems = session.createQuery("update Employee e set e.company = 'Google Inc.' where e.company = 'Google'").executeUpdate();
			System.out.println("Number of updated items : " + numOfUpdatedItems);
			session.getTransaction().commit();
			
			//Delete
			session = factory.getCurrentSession();
			session.beginTransaction();
			int numOfDeletedItems = session.createQuery("delete Employee e where e.company = 'Amazon'").executeUpdate();
			System.out.println("Number of deleted items : " + numOfDeletedItems);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
