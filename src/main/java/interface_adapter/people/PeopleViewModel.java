package interface_adapter.people;

import interface_adapter.ViewModel;


public class PeopleViewModel extends ViewModel<PeopleState> {

    public PeopleViewModel() {
        super("people");
        setState(new PeopleState());
    }

}
