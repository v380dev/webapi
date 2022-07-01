package ua.v380dev.model.states;

import ua.v380dev.model.entitys.Endpoint;

import java.util.List;
import java.util.Optional;

public class StateRequest extends State {
    private String nameEndpoint;
    private int numberLineEndp;

    public StateRequest(String nameEndpoint, int numberLineEndp, List<String> objects, List<Endpoint> endpoints) {
        super(objects, endpoints);
        this.nameEndpoint = nameEndpoint;
        this.numberLineEndp = numberLineEndp;
    }

    @Override
    protected State lineLikeNewObject() {
        return new StateOut(objects, endpoints);
    }

    @Override
    protected State lineLikeResponse() {
        return new StateResponse(nameEndpoint, numberLineEndp, objects, endpoints);
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
        for (String obj : objects) {
            if (currentLine.contains(String.format("`%s`", obj))) {
                Endpoint endpoint;
                Optional<Endpoint> optional = Endpoint.getEndpointFromList(endpoints, nameEndpoint, numberLineEndp);
                if (optional.isEmpty()) {
                    endpoint = new Endpoint(nameEndpoint, numberLineEndp);
                    endpoints.add(endpoint);
                } else {
                    endpoint = optional.get();
                }
                endpoint.getRequests().add(obj);
            }
        }
        return this;
    }
}
