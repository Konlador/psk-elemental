package lt.psk.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQueries({
    @NamedQuery(name = "Elem.findAll", query = "select e from Elem as e")
})
@Table(name = "ELEMS")
@Getter @Setter
public class Elem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    private String category;
    private String color;
    private Date createdOn;

    public Elem() { }

    public Elem(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elem element = (Elem) o;
        return id == element.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
