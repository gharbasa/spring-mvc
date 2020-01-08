package spring.mvc.model;

//@XmlRootElement(name = "student")
public class Student {
		
	//@XmlElement
	private Integer age;
	   
	//@XmlElement
	private String name;
	
	//@XmlElement
	private Integer id;

	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}
		
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
		
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
}
