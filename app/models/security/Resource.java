package models.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import play.data.validation.MinSize;
import play.db.jpa.Model;

@Entity(name = "T_RESOURCE")
public class Resource extends Model {
    // 只能通过create or fetch创建
    private Resource() {
    }

    @MinSize(value = 2)
    @Column(nullable = false, unique = true)
    public String name;
    
    @MinSize(value = 1)
    @Column(nullable = false)
    public String method;
    
    @Column(nullable = false, unique = true)
    @MinSize(value = 2)
    public String resource;
    @ManyToMany(mappedBy = "resources")
    public List<UserGroup> userGroups = new ArrayList<UserGroup>();

    @Transient
    public boolean checked;
    
    public Resource(String name, String method, String resource) {
		this.name = name;
		this.method = method;
		this.resource = resource;
	}

    /**
     * 新建一个资源，如果数据库已有则不必新建
     */
    public static Resource createOrFetch(String name, String resource, String method) {
        Resource r = Resource.find("name = ? and resource = ? ", name, resource).first();
        if (r != null) {
            return r;
        }
        return new Resource(name, resource, method);
    }
}
