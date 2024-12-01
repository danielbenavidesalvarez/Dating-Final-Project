package interface_adapter.people;

import entity.User;

import java.util.ArrayList;
import java.util.List;

public class PeopleState {
    private List<User> people;

    public PeopleState(List<User> people) {
        this.people = people;
    }

    public PeopleState() {
        people = new ArrayList<>();
    }
    public List<User> getPeople() {
        return people;
    }
}
