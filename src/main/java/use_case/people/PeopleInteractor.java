package use_case.people;

import entity.User;

import java.util.List;

public class PeopleInteractor implements PeopleInputBoundary {
    private final PeopleOutputBoundary peoplepresenter;
    private final PeopleUserDataAccessInterface dataAccess;

    public PeopleInteractor(PeopleOutputBoundary peoplepresenter, PeopleUserDataAccessInterface dataAccess) {
        this.peoplepresenter = peoplepresenter;
        this.dataAccess = dataAccess;

    }


    @Override
    public void execute(PeopleInputData peopleInputData) {
        PeopleOutputData peopleOutputData = new PeopleOutputData();

        List<User> databaseusers = dataAccess.getUsers();
        peopleOutputData.getPeople().addAll(databaseusers);
//        List<CommonUser> databaseusers = new ArrayList<>();
//        databaseusers.add(new CommonUser("ya", "123"));
//        databaseusers.add(new CommonUser("da", "456"));

//        for (User user : databaseusers) {
//            JButton newbutton = new JButton(user.getName());
//            peopleOutputData.getPeople().add(newbutton);
//        }

        peoplepresenter.present(peopleOutputData);

    }
}