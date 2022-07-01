package ua.v380dev.model.states;

import ua.v380dev.model.entitys.Endpoint;

import java.util.List;

public class StateEndpoint extends State {
    String nameEndpoint;
    int currentNumberLine;

    public StateEndpoint(String currentLine, int currentNumberLine, List<String> objects, List<Endpoint> endpoints) {
        super(objects, endpoints);
        this.nameEndpoint = currentLine;
        this.currentNumberLine = currentNumberLine;
    }


    @Override
    protected State lineLikeNewObject() {
        return new StateOut(objects, endpoints);
    }

    @Override
    protected State lineLikeResponse() {
        return new StateResponse(nameEndpoint, currentNumberLine, objects, endpoints);
    }

    @Override
    protected State lineLikeRequest() {
        return new StateRequest(nameEndpoint, currentNumberLine, objects, endpoints);
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
