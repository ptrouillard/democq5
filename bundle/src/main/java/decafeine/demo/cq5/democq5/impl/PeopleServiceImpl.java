package decafeine.demo.cq5.democq5.impl;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.result.SearchResult;
import decafeine.demo.cq5.democq5.PeopleDTO;
import decafeine.demo.cq5.democq5.PeopleDTOBuilder;
import decafeine.demo.cq5.democq5.PeopleService;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.search.QueryBuilder;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Service
@Component(metatype = false)
public class PeopleServiceImpl implements PeopleService {

    private static final Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);

    @Reference
    private SlingRepository repository;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private QueryBuilder queryBuilder;

    private ResourceResolver resourceResolver;

    @Override
    public List<PeopleDTO> list() {

        List<PeopleDTO> contacts = new ArrayList();
        Session session = null;
        try {
            session = repository.loginAdministrative(null);
            Resource contactsResource = resourceResolver.resolve("/content/contacts");

            logger.info("contactsResource={}", contactsResource.toString());
            Iterator<Resource> resourceIterator = contactsResource.listChildren();
            while (resourceIterator.hasNext()) {
                Resource res = resourceIterator.next();
                PeopleDTO dto = PeopleDTOBuilder.build().withNode(res.adaptTo(Node.class)).get();
                contacts.add(dto);
                logger.info("resource={}", res.toString());
                logger.info("contact={}", (dto != null ? dto.toString() : "null"));
            }
        } catch (RepositoryException e) {
            logger.error(e.getMessage(), e);
        } finally {
            session.logout();
        }
        return contacts;
    }

    @Override
    public List<PeopleDTO> search(String lastName) {

        List<PeopleDTO> contacts = new ArrayList();
        Session session = null;
        try {
            session = repository.loginAdministrative(null);

            logger.info("Search by last name '{}'", lastName);

            Map map = new HashMap();
            map.put("property", "lastName");
            map.put("property.value", lastName);
            map.put("type", "sling:OrderedFolder");

            Query query = queryBuilder.createQuery(PredicateGroup.create(map), session);
            query.setStart(0);
            query.setHitsPerPage(1000);
            SearchResult result = query.getResult();
            Iterator<Resource> resources = result.getResources();
            while (resources.hasNext()) {
                Resource next = resources.next();
                PeopleDTO dto = PeopleDTOBuilder.build().withNode(next.adaptTo(Node.class)).get();
                contacts.add(dto);
            }
            logger.info("Search by last name '{}' : {} contacts found", lastName, contacts.size());

        } catch (RepositoryException e) {
            logger.error(e.getMessage(), e);
        } finally {
            session.logout();
        }
        return contacts;
    }


    @Activate
    protected void activate(ComponentContext context) throws RepositoryException {
        try {
            this.resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);

        } catch (LoginException e) {
            logger.error("ENABLE to get resolver ", e);
        }

    }

    @Deactivate
    protected void deactivate(ComponentContext componentContext) {
        if (resourceResolver != null) {
            resourceResolver.close();
            resourceResolver = null;
        }
    }

}
