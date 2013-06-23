package controllers.admin;

import java.util.List;

import models.security.User;
import models.security.UserGroup;
import play.data.validation.Valid;
import controllers.Application;

public class Users extends Application {

    public static void index() {
        List<User> users = User.findAll();
        boolean userAdmin = true;
        render("Admin/user/users.html", users, userAdmin);
    }

    public static void edit(long id) {
        User user = User.findById(id);
        List<UserGroup> groups = UserGroup.findAll();
        render("Admin/user/user_edit.html", user, groups);
    }

    public static void save(@Valid User user) {
        user.save();
        index();
    }

    public static void delete(long id) {
        User.delete("id = ?", id);
        index();
    }
}
