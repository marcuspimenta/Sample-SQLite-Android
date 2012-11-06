package com.sqlite.domain;

/**
 * 		
 * @author Marcus Pimenta
 * @email mvinicius.pimenta@gmail.com
 * @date 19:44:01 04/11/2012
 */
public class User {
	
	private String name;
    private String profession;
    private Integer age;
    
    public User(String name, String profession, Integer age) {
        this.name = name;
        this.profession = profession;
        this.age = age;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
    
}