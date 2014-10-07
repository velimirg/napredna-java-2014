package hr.calyx.vjestina2014.services;

import hr.calyx.vjestina2014.domain.DummyClass;
import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService {
    @Override
    public String getDummyString() {
        return "Servis govori, ovo je Dummy String";
    }

    @Override
    public DummyClass getDummyObject() {
        DummyClass retval = new DummyClass();
        retval.setAge(12);
        retval.setId(1L);
        retval.setName("Luka Modric");

        return retval;
    }
}
