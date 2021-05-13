package it.befloral.beans;

import java.io.Serializable;

public class CustomerBean implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int id ;
    private String fristname;
    private String lastname;
    private String email;
    private int gender;
    private int active;
    private String newsletter;
    private String password;
    private java.sql.Date birthday;


    public CustomerBean () {

    }

    
    public int getId() {
          return this.id ;
    }
    public void setId(int idIn) {
          this.id  = idIn;
    }

    public String getFristname() {
          return this.fristname;
    }
    public void setFristname(String fristnameIn) {
          this.fristname = fristnameIn;
    }

    public String getLastname() {
          return this.lastname;
    }
    public void setLastname(String lastnameIn) {
          this.lastname = lastnameIn;
    }

    public String getEmail() {
          return this.email;
    }
    public void setEmail(String emailIn) {
          this.email = emailIn;
    }

    public int getGender() {
          return this.gender;
    }
    public void setGender(int genderIn) {
          this.gender = genderIn;
    }

    public int getActive() {
          return this.active;
    }
    public void setActive(int activeIn) {
          this.active = activeIn;
    }

    public String getNewsletter() {
          return this.newsletter;
    }
    public void setNewsletter(String newsletterIn) {
          this.newsletter = newsletterIn;
    }

    public String getPassword() {
          return this.password;
    }
    public void setPassword(String passwordIn) {
          this.password = passwordIn;
    }

    public java.sql.Date getBirthday() {
          return this.birthday;
    }
    public void setBirthday(java.sql.Date birthdayIn) {
          this.birthday = birthdayIn;
    }


    public void setAll(int idIn,
          String fristnameIn,
          String lastnameIn,
          String emailIn,
          int genderIn,
          int activeIn,
          String newsletterIn,
          String passwordIn,
          java.sql.Date birthdayIn) {
          this.id  = idIn;
          this.fristname = fristnameIn;
          this.lastname = lastnameIn;
          this.email = emailIn;
          this.gender = genderIn;
          this.active = activeIn;
          this.newsletter = newsletterIn;
          this.password = passwordIn;
          this.birthday = birthdayIn;
    }



    public boolean hasEqualMapping(CustomerBean valueObject) {

          if (valueObject.getId() != this.id ) {
                    return(false);
          }
          if (this.fristname == null) {
                    if (valueObject.getFristname() != null)
                           return(false);
          } else if (!this.fristname.equals(valueObject.getFristname())) {
                    return(false);
          }
          if (this.lastname == null) {
                    if (valueObject.getLastname() != null)
                           return(false);
          } else if (!this.lastname.equals(valueObject.getLastname())) {
                    return(false);
          }
          if (this.email == null) {
                    if (valueObject.getEmail() != null)
                           return(false);
          } else if (!this.email.equals(valueObject.getEmail())) {
                    return(false);
          }
          if (valueObject.getGender() != this.gender) {
                    return(false);
          }
          if (valueObject.getActive() != this.active) {
                    return(false);
          }
          if (this.newsletter == null) {
                    if (valueObject.getNewsletter() != null)
                           return(false);
          } else if (!this.newsletter.equals(valueObject.getNewsletter())) {
                    return(false);
          }
          if (this.password == null) {
                    if (valueObject.getPassword() != null)
                           return(false);
          } else if (!this.password.equals(valueObject.getPassword())) {
                    return(false);
          }
          if (this.birthday == null) {
                    if (valueObject.getBirthday() != null)
                           return(false);
          } else if (!this.birthday.equals(valueObject.getBirthday())) {
                    return(false);
          }

          return true;
    }



 

    @Override
	public String toString() {
		return "CustomerBean [id=" + id  + ", fristname=" + fristname + ", lastname=" + lastname + ", email=" + email
				+ ", gender=" + gender + ", active=" + active + ", newsletter=" + newsletter + ", password=" + password
				+ ", birthday=" + birthday + "]";
	}


	public Object clone() {
        CustomerBean cloned = new CustomerBean();

        cloned.setId(this.id ); 
        if (this.fristname != null)
             cloned.setFristname(new String(this.fristname)); 
        if (this.lastname != null)
             cloned.setLastname(new String(this.lastname)); 
        if (this.email != null)
             cloned.setEmail(new String(this.email)); 
        cloned.setGender(this.gender); 
        cloned.setActive(this.active); 
        if (this.newsletter != null)
             cloned.setNewsletter(new String(this.newsletter)); 
        if (this.password != null)
             cloned.setPassword(new String(this.password)); 
        if (this.birthday != null)
             cloned.setBirthday((java.sql.Date)this.birthday.clone()); 
        return cloned;
    }


}
