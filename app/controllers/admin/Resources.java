package controllers.admin;

import java.util.List;

import models.security.Resource;
import models.security.UserGroup;
import play.data.validation.Valid;
import controllers.Application;


public class Resources extends Application {

    public static void index() {
        List<Resource> resources = Resource.findAll();
        boolean resourceAdmin = true;
        render("Admin/user/resources.html", resourceAdmin, resources);
    }

    public static void edit(long id) {
        Resource resource = null;
        if (id > 0) {
            resource = Resource.findById(id);
        }
        render("Admin/user/resource_edit.html", resource);
    }

    public static void save(@Valid Resource resource) {
        resource.save();
        index();
    }

    public static void delete(long id) {
        if (id > 0) {
            Resource r = Resource.findById(id);
            // 从资源中先删除所有分组, UserGroup和Resource的关联中UserGroup是owner.
            // Owner方可以自动维护关系，被维护方需要手动维护
            for (UserGroup group : r.userGroups) {
                group.resources.remove(r);
                group.save();
            }
            r.delete();
        }
        index();
    }
}
