package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name")
   private String name;

   @Column(name = "email")
   private String email;
   
   @Column(name = "phone")
   private String phone;
   
   @Column(name = "address")
   private String address;

   public Employee() {
   }

   public Employee(Integer id, String name, String email, String phone, String address) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.address = address;
   }

   public Employee(String name, String email, String phone, String address) {
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.address = address;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getPhone() {
	  return phone;
   }

   public void setPhone(String phone) {
	   this.phone = phone;
   }
	   
   public String getAddress() {
	   return address;
   }

	public void setAddress(String address) {
		this.address = address;
	}

   @Override
   public String toString() {
      return "Contact: " + this.id + ", " + this.name + ", " + this.email + ", " + this.phone + ", " + this.address;
   }
   
   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       Employee employee = (Employee) o;
       return Objects.equals(id, employee.id) &&
              Objects.equals(name, employee.name) &&
              Objects.equals(email, employee.email) &&
              Objects.equals(phone, employee.phone) &&
              Objects.equals(address, employee.address);
   }

   @Override
   public int hashCode() {
       return Objects.hash(id, name, email, phone, address);
   }

}