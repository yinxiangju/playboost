package controllers.admin;

import java.util.List;

import models.security.Resource;
import models.security.UserGroup;
import play.data.validation.Valid;
import controllers.Application;


public class UserGroups extends Application {


    public static void index() {
        List<UserGroup> groups = UserGroup.findAll();
        boolean groupAdmin = true;
        render("Admin/user/groups.html", groups, groupAdmin);
    }

    public static void edit(long id) {
        UserGroup group = UserGroup.findById(id);
        List<Resource> resources = Resource.findAll();
        if (group != null) {
            for (Resource resource : resources) {
                if (group.resources.contains(resource)) {
                    resource.checked = true;
                }
            }
        }
        render("Admin/user/group_edit.html", group, resources);
    }

    public static void save(@Valid UserGroup group) {
        group.save();
        index();
    }

    public static void delete(long id) {
        UserGroup group = UserGroup.findById(id);
        // 从资源中先删除所有分组, UserGroup和Resource的关联中UserGroup是owner.
        // Owner方可以自动维护关系，被维护方需要手动维护
        group.delete();
        index();
    }
}
