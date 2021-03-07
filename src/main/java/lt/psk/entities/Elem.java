package lt.psk.entities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import java.util.Date;

@RequestScoped
public class Elem {

    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString();
    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}
