package it.befloral.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerBean implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int id ;
    private String firstName;
    private String lastName;
    private int gender;
    private boolean subscription;
    private LocalDate birthday;
    private UserBean user;

    public CustomerBean () {

    }
    
    public UserBean getUser() {
    	return user;
    }
    public void setUser(UserBean user) {
    	this.user = user;
    }

    public int getId() {
          return this.id ;
    }
    public void setId(int idIn) {
          this.id  = idIn;
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

    public int getGender() {
          return this.gender;
    }
    public void setGender(int genderIn) {
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


    public void setAll(int idIn,String firstNameIn,String lastNameIn,int genderIn,
        boolean subscription,LocalDate birthdayIn) {
          this.id  = idIn;
          this.firstName = firstNameIn;
          this.lastName = lastNameIn;
          this.gender = genderIn;
          this.subscription = subscription;
          this.birthday = birthdayIn;
    }



    public boolean hasEqualMapping(CustomerBean valueObject) {

          if (valueObject.getId() != this.id ) {
                    return(false);
          }
          if (this.firstName == null) {
                    if (valueObject.getFirstName() != null)
                           return(false);
          } else if (!this.firstName.equals(valueObject.getFirstName())) {
                    return(false);
          }
          if (this.lastName == null) {
                    if (valueObject.getLastName() != null)
                           return(false);
          } else if (!this.lastName.equals(valueObject.getLastName())) {
                    return(false);
          }
          if (valueObject.getGender() != this.gender) {
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
		return "CustomerBean [id=" + id  + ", fristname=" + firstName + ", lastname=" + lastName
				+ ", gender=" + gender + ", newsletter=" + isSubscripted()
				+ ", birthday=" + birthday + "]";
	}


	public Object clone() {
        CustomerBean cloned = new CustomerBean();

        cloned.setId(this.id ); 
        if (this.firstName != null)
             cloned.setFirstName(new String(this.firstName)); 
        if (this.lastName != null)
             cloned.setLastName(new String(this.lastName)); 
        cloned.setGender(this.gender); 
        cloned.setSubscription(this.isSubscripted()); 
        if (this.birthday != null)
             cloned.setBirthday((LocalDate)this.birthday); 
        return cloned;
    }


}
