/**
 * 
 */
package com.angus.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author weipeng
 *
 */
public class Java8Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> people = new ArrayList<>();

		people.add(new Person("Mohamed", 69));
		people.add(new Person("Doaa", 25));
		people.add(new Person("Malik", 6));

		Predicate<Person> pred = (p) -> p.getAge() > 65;

		displayPeople(people, pred);

	}

	private static void displayPeople(List<Person> people, Predicate<Person> pred) {

		System.out.println("Selected:");
		people.forEach(p -> {
			if (pred.test(p)) {
				System.out.println(p.getName());
			}
		});
		people.stream().forEach(p -> System.out.println(p.getName()));
	}
}

class Person {
	private int age;
	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Person(String name, int age) {
		this.name = name;
		this.age = age;

	}
}