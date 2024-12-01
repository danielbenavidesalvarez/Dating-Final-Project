package use_case.edit_profile;

/**
 * Input data for the Edit Profile use case.
 */
public class EditProfileInputData {
    private final String userId;
    private final String name;
    private final int age;
    private final String interests;

    public EditProfileInputData(String userId, String name, int age, String interests) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.interests = interests;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getInterests() {
        return interests;
    }
}

