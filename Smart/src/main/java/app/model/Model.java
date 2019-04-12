package app.model;

import java.util.ArrayList;

public class Model {



    private ArrayList<Output > model;

    public static Model getInstance() {
        return new Model() ;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(Output output) {
        model.add(output);
    }
    public ArrayList<Output > getList() {
        return model;
    }

}
