package use_case.people;

import entity.CommonUser;

import java.util.List;

public class PeopleInputData {
    private List<CommonUser> users;

    public PeopleInputData(List<CommonUser> users) {
        this.users = users;
    }
    public List<CommonUser> getUsers() {
        return users;
    }

}
