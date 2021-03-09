package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Super class to the Student and Registrar classes
 * Provides getter and setter methods for the firstName, lastName, id, email, and password fields
 * Provides an initial constructor using the firstName, lastName, id, email, and password fields
 * @author Joel McKinney
 * @author Ahmed
 */
public abstract class User {

    /** first name of the student */
    private String firstName;
    /** last name of the student */
    private String lastName;
    /** unity id of the student */
    private String id;
    /** the email of the student */
    private String email;
    /** the password of the student */
    private String password;

    /**
     * Constructs a User object using all class fields
     * @param firstName the first name of a user
     * @param lastName the last name of a user
     * @param id the unity id of a user
     * @param email the email of a user
     * @param password the encoded password of a user
     */
    public User(String firstName, String lastName, String id, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
        setEmail(email);
        setPassword(password);
    }
    
    /**
     * Returns the student's first name
     * @return firstName Student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /** 
     * Sets the student's first name
     * Input cannot be null or empty
     * @throws IllegalArgumentException if the String is null
     * @param firstName Student's first name
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() == 0) {
            throw new IllegalArgumentException("Invalid first name");
        }
        else {
            this.firstName = firstName;
        }
    }

    /**
     * gets the student's last name
     * @return lastName student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the student's last name
     * Input cannot be empty
     * @throws IllegalArgumentException if the String is null or empty
     * @param lName the student's last name
     */
    public void setLastName(String lName) 
    {
        if (lName == null || lName.length() == 0) {
            throw new IllegalArgumentException("Invalid last name");
        }
        else {
            this.lastName = lName;
        }
    }

    /**
     * gets the student's id
     * @return the studnet's id
     */
    public String getId() {
        return id;
    }

    /**
     * sets the student's id
     * The input cannot be null or empty
     * @throws IllegalArgumentException if the String is null
     * @param id the student's id
     */
    public void setId(String id)
    {
        if (id == null || id.length() == 0) {
            throw new IllegalArgumentException("Invalid id");
        }
        else {
            this.id = id;
        }
    }

    /**
     * gets the student's email
     * @return the student's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the students email
     * Checks to see if the input contains an @ and .
     * @throws IllegalArgumentException if the String is null
     * @throws IllegalArgumentException if the String doesn't contain an '@' or '.'
     * @throws IllegalArgumentException if there is no '.' after an '@'
     * @param email the student's email
     */
    public void setEmail(String email ) {
        boolean containsAt = false;
        boolean containsPeriod = false;
        int indexOfPeriod = 0;
        int indexOfAt = 0;
        if (email != null && email.length() != 0) {
            for (int i = 0; i < email.length(); i++) {
                char current = email.charAt(i);
                if (current == '@') {
                    containsAt = true;
                    indexOfAt = i;
                }
                if (current == '.') {
                    containsPeriod = true;
                    indexOfPeriod = i;
                }
            }

            if (!containsAt || !containsPeriod || indexOfPeriod < indexOfAt) {
                throw new IllegalArgumentException("Invalid email");
            }
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    /**
     * gets the student's password
     * @return the student's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the student's password
     * @throws IllegalArgumentException if the String is null
     * @param password the student's password
     */
    public void setPassword(String password) {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Invalid password");
        }
        else {
            this.password = password;
        }
    }
    
    /**
     * Overrides hashCode 
     * Ensures that the hashCodes are equal
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }


    /**
     * Overrides equals
     * Checks to see if two student objects are equivalent 
     * @param obj the object to compare to a student object for equivalence. 
     */
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
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

}