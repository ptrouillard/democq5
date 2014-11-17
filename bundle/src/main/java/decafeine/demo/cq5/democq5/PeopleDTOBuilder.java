package decafeine.demo.cq5.democq5;

import org.apache.sling.api.resource.Resource;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

public class PeopleDTOBuilder {

    private String firstName;
    private String lastName;
    private String email;

    public static PeopleDTOBuilder build() {
        return new PeopleDTOBuilder();
    }

    public PeopleDTO get() {
        return new PeopleDTO(this.firstName, this.lastName, this.email);
    }

    public PeopleDTOBuilder withNode(Node contactNode) throws RepositoryException {
        this.firstName = contactNode.getProperty("firstName").getString();
        this.lastName = contactNode.getProperty("lastName").getString();
        this.email = contactNode.getProperty("email").getString();
        return this;
    }
}
