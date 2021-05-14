package it.befloral.loggedControl;

public class User {
	
	
	private int id ;
    private String fristname;
    private String lastname;
    private String email;
    
    
    
    
    
	public User(int id, String fristname, String lastname, String email) {
		super();
		this.id = id;
		this.fristname = fristname;
		this.lastname = lastname;
		this.email = email;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", fristname=" + fristname + ", lastname=" + lastname + ", email=" + email + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fristname == null) ? 0 : fristname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fristname == null) {
			if (other.fristname != null)
				return false;
		} else if (!fristname.equals(other.fristname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFristname() {
		return fristname;
	}
	public void setFristname(String fristname) {
		this.fristname = fristname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getWelcome() {
		return "Welcome "+getFristname()+"!";
	}
	
	
	
  
}
