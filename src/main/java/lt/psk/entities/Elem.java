package lt.psk.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name = "Elem.findAll", query = "select e from Elem as e"),
    @NamedQuery(name = "Elem.findByName", query = "select e from Elem as e WHERE e.name = :name")
})
@Table(name = "ELEMS")
@Getter @Setter
public class Elem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String category;

    public Elem() { }

    public Elem(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elem element = (Elem) o;
        return Objects.equals(name, element.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
