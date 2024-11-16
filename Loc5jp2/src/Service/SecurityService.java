package Service;

import Entity.Security;

import java.util.List;
import java.util.stream.Collectors;

    public class SecurityService {

        public List<Security> filterByType(List<Security> securities, String type) {
            return securities.stream()
                    .filter(s -> s.getType().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }
    }

