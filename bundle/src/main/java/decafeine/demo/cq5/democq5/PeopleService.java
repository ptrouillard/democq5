package decafeine.demo.cq5.democq5;

import java.util.List;

public interface PeopleService {
    List<PeopleDTO> list();
    List<PeopleDTO> search(String lastName);
}
