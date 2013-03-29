package models.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity(name = "T_RESOURCE")
public class Resource extends Model {
    @Column(nullable = false, unique = true)
    public String name;
    @Column(nullable = false, unique = true)
    public String resource;
    @ManyToMany
    public List<UserGroup> userGroups = new ArrayList<UserGroup>();

    public Resource(String name, String resource) {
        this.name = name;
        this.resource = resource;
    }

    /**
     * 新建一个资源，如果数据库已有则不必新建
     */
    public static Resource createOrFetch(String name, String resource) {
        Resource r = Resource.find("name = ? and resource = ? ", name, resource).first();
        if (r != null) {
            return r;
        }
        return new Resource(name, resource);
    }
}
