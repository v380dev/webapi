package ua.v380dev.model.entitys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Endpoint {
    private String name;
    private int numberLine;
    private List<String> requests;
    private List<String> responses;

    public Endpoint(String name, int numberLine) {
        this.name = name;
        this.numberLine = numberLine;
        this.requests = new ArrayList<>();
        this.responses = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRequests() {
        return requests;
    }

    public void setRequests(List<String> requests) {
        this.requests = requests;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public int getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(int numberLine) {
        this.numberLine = numberLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endpoint endpoint = (Endpoint) o;
        return numberLine == endpoint.numberLine &&
                Objects.equals(name, endpoint.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberLine);
    }

    @Override
    public String toString() {
        return numberLine + ", " +
                name + ", " +
                "REQ=" + requests +  ", " +
                "RES=" + responses;
    }


    public static Optional<Endpoint> getEndpointFromList(List<Endpoint> endpoints, String nameEndpoint, int numberLine) {
        Endpoint endpoint;
        for (int i = 0; i < endpoints.size(); i++) {
            endpoint = endpoints.get(i);
            if (endpoint.getName().equals(nameEndpoint) && endpoint.getNumberLine() == numberLine) {
                return Optional.of(endpoint);
            }
        }
        return Optional.empty();
    }
}
