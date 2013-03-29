package models.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity(name = "T_USER_GROUP")
public class UserGroup extends Model {
    @Column(nullable = false, unique = true)
    public String name;
    @OneToMany(mappedBy = "userGroup")
    public List<User> users;
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Resource> resources = new ArrayList<Resource>();
}
