public class User {
    private String username;
    private int age;
    private String gender;
    private String location;
    private String interests;

    public User(String username, int age, String gender, String location, String interests) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.interests = interests;
    }

    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getLocation() { return location; }
    public String getInterests() { return interests; }

    @Override
    public String toString() {
        return "Username: " + username + ", Age: " + age + ", Gender: " + gender +
                ", Location: " + location + ", Interests: " + interests;
    }
}

