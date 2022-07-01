package ua.v380dev.model.states;

import ua.v380dev.model.entitys.Endpoint;

import java.util.List;

public class StateOut extends State {

    public StateOut(List<String> objects, List<Endpoint> endpoints) {
        super(objects, endpoints);
    }


    @Override
    protected State lineLikeNewObject() {
        return this;
    }

    @Override
    protected State lineLikeResponse() {
        return this;
    }

    @Override
    protected State lineLikeRequest() {
        return this;
    }

    @Override
    protected State lineLikeEndpoint(String currentLine, int currentNumberLine) {
        return new StateEndpoint(currentLine, currentNumberLine, objects, endpoints);
    }

    @Override
    protected State anotherLine(String currentLine) {
        return this;
    }
}
