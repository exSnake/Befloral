package it.befloral.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class User implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
    private String lastName;
    private String gender;
    private boolean subscription;
    private LocalDate birthday;
    private Collection<Address> addresses;
	private String email;
	private String password;
	private String role;
	private boolean active;

	
	public boolean isFemale() {
		return gender.equals("Female") ? true : false;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
        return this.firstName;
  }

  public void setFirstName(String firstName) {
        this.firstName = firstName;
  }

  public String getLastName() {
        return this.lastName;
  }

  public void setLastName(String lastName) {
        this.lastName = lastName;
  }

  public String getGender() {
        return this.gender;
  }

  public void setGender(String genderIn) {
        this.gender = genderIn;
  }

  public boolean isSubscripted() {
        return this.subscription;
  }

  public void setSubscription(boolean subscription) {
        this.subscription = subscription;
  }

  public LocalDate getBirthday() {
        return this.birthday;
  }

  public void setBirthday(LocalDate birthdayIn) {
        this.birthday = birthdayIn;
  }
  
  public Collection<Address> getAddresses(){
	  return this.addresses;
  }
  
  public Address getPreferredAddress() {
	  if(this.addresses == null)
		  return null;
	  for(Address a: this.addresses) {
		  if(a.isPreferred())
			  return a;
	  }
	  return null;
  }
  
  public void addAddress(Address addr) {
	  if(this.addresses == null)
		  this.addresses = new ArrayList<Address>();
	  this.addresses.add(addr);
  }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", subscription=" + subscription + ", birthday=" + birthday + ", addresses=" + addresses + ", email="
				+ email + ", password=" + password + ", role=" + role + ", active=" + active + "]";
	}

	public boolean setPreferredAddress(int addressId) {
		var found = false;
		var prev = this.getPreferredAddress();
		for (Address addr : addresses) {
			if(addr.getId() == addressId) {
				addr.setPreferred(true);
				found = true;
				break;
			}
		}
		if(found) {
			for (Address addr : addresses) {
				if(addr.getId() != addressId) {
					addr.setPreferred(false);
				}
			}
		}
		return found;
	}

	

}
