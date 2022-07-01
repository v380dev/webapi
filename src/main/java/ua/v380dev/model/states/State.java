package ua.v380dev.model.states;

import ua.v380dev.model.entitys.Endpoint;
import ua.v380dev.model.util.HolderPattern;

import java.util.List;

public abstract class State {
    List<String> objects;
    List<Endpoint> endpoints;

    public State(List<String> objects, List<Endpoint> endpoints) {
        this.objects = objects;
        this.endpoints = endpoints;
    }

    public State update(String currentLine, int currentNumberLine) {
        if (HolderPattern.getEndp().matcher(currentLine).find()) {
            return lineLikeEndpoint(currentLine, currentNumberLine);
        }
        if (HolderPattern.getReq().matcher(currentLine).find()) {
            return lineLikeRequest();
        }
        if (HolderPattern.getRes().matcher(currentLine).find()) {
            return lineLikeResponse();
        }
        if (HolderPattern.getObj().matcher(currentLine).find()) {
            return lineLikeNewObject();
        }
        return anotherLine(currentLine);
    }

    protected abstract State lineLikeNewObject();

    protected abstract State lineLikeResponse();

    protected abstract State lineLikeRequest();

    protected abstract State lineLikeEndpoint(String currentLine, int currentNumberLine);

    protected abstract State anotherLine(String currentLine);
}
