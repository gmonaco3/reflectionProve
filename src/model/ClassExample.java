package model;

import java.util.Date;

import reflection.Column;
import reflection.PrimaryKey;

public class ClassExample extends SuperClassExample implements InterfaceExample {

	@PrimaryKey
	private Long id;
	@Column
	private String name;
	@Column
	private String lastName;
	@Column
	private Date date;
	
	private OtherClassExample otherClassExample;

	public ClassExample() {
	}

	public ClassExample(Long id, String name, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
//		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "ClassExample [id=" + id + ", name=" + name + ", lastName=" + lastName + ", date=" + date
				+ ", otherClassExample=" + otherClassExample + "]";
	}

	

	

//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}

}
