package star.reusable.manager;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import star.reusable.dao.domain.Company;

/**
 * @author Luke Xie
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyManager {

    public List<Company> list() {
        List<Company> list = new ArrayList<>();
        list.add(new Company("企业1", "111111"));
        list.add(new Company("企业2", "222222"));
        return list;
    }
}
