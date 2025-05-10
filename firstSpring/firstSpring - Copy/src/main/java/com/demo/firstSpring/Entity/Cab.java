
package com.demo.firstSpring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cab {
	@Id
	private long id; 
	private String city; 
	private String nameofDirver;
    private long price; 

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNameofDirver() {
		return nameofDirver;
	}

	public void getNameofDirver(String nameofDirver) {
		this.nameofDirver = nameofDirver;
	}
    public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Cab(long id, String city, String nameofDriver , long price) {
		super();
		this.id = id;
		this.city = city;
		this.nameofDirver = nameofDriver;
        this.price = price;
	}

	public Cab() {
		super(); 
		
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", city=" + city + ", nameofDirver" + nameofDirver + ", getId()=" + getId()
				+ ", getCity()=" + getCity() + ", getNameofDirver()=" + getNameofDirver() + ", getPrice()=" + getPrice() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
