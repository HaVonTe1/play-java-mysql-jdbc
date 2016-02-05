package de.webtrekk.dp.chapter.play.models;


public class Person {

	public int id;

    public String firstname;
    public String lastname;
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + "]";
	}
}
