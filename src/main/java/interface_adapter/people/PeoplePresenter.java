package interface_adapter.people;

import use_case.people.PeopleOutputBoundary;
import use_case.people.PeopleOutputData;

public class PeoplePresenter implements PeopleOutputBoundary {
    private PeopleViewModel peopleViewModel;

    public PeoplePresenter(PeopleViewModel peopleViewModel) {
        this.peopleViewModel = peopleViewModel;
    }


    @Override
    public void present(PeopleOutputData peopleOutputData) {
        peopleViewModel.setState(new PeopleState(peopleOutputData.getPeople()));
        peopleViewModel.firePropertyChanged("Show People");

    }
}
