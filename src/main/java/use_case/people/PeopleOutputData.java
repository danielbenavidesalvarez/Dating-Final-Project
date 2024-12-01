package use_case.people;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class PeopleOutputData {
    private List<User> people;

    public PeopleOutputData() {
        people = new ArrayList<>();
    }

    public List<User> getPeople() {
        return people;
    }
}
