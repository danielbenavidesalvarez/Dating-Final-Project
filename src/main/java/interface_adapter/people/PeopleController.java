package interface_adapter.people;

import use_case.people.PeopleInputBoundary;
import use_case.people.PeopleInputData;

import java.util.ArrayList;

public class PeopleController {

    private final PeopleInputBoundary peopleinteractor;


    public PeopleController(PeopleInputBoundary peopleinteractor) {
        this.peopleinteractor = peopleinteractor;
    }

    public void showpeople() {
        // Create input data and delegate the request to the interactor
        PeopleInputData inputData = new PeopleInputData(new ArrayList<>());

        peopleinteractor.execute(inputData);
    }
}