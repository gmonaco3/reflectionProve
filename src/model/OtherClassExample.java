package model;

import java.util.Date;

import reflection.Column;
import reflection.PrimaryKey;

public class OtherClassExample {
	@PrimaryKey
	private Long idOtherClass;
	@Column
	private String nameOtherClass;
	@Column
	private String lastNameOtherClass;
	@Column
	private Date date;

	public Long getIdOtherClass() {
		return idOtherClass;
	}

	public void setIdOtherClass(Long idOtherClass) {
		this.idOtherClass = idOtherClass;
	}

	public String getNameOtherClass() {
		return nameOtherClass;
	}

	public void setNameOtherClass(String nameOtherClass) {
		this.nameOtherClass = nameOtherClass;
	}

	public String getLastNameOtherClass() {
		return lastNameOtherClass;
	}

	public void setLastNameOtherClass(String lastNameOtherClass) {
		this.lastNameOtherClass = lastNameOtherClass;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "OtherClassExample [idOtherClass=" + idOtherClass + ", nameOtherClass=" + nameOtherClass
				+ ", lastNameOtherClass=" + lastNameOtherClass + ", date=" + date + "]";
	}

	

}
