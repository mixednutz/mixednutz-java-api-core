package net.mixednutz.api.core.model.v1_9;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class UserProfile {

	//Extend properties from User object
	private Location location;
	private LocalDate birthday;
	private Integer age;
	private ZonedDateTime lastlogin;
	
	// Properties from Myprofile object
	private String aboutMe;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public ZonedDateTime getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(ZonedDateTime lastlogin) {
		this.lastlogin = lastlogin;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
		
}
