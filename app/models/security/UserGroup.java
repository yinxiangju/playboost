package models.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.db.jpa.Model;

@Entity(name = "T_USER_GROUP")
public class UserGroup extends Model {
    @Column(nullable = false, unique = true)
    @MinSize(value = 2)
    public String name;
    @MaxSize(value = 1024)
    public String detail;
    @OneToMany(mappedBy = "userGroup")
    public List<User> users;
    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Resource> resources = new ArrayList<Resource>();

    public int getUserNum() {
        return this.users.size();
    }
}
