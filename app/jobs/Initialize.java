package jobs;

import models.security.Resource;
import models.security.User;
import models.security.UserGroup;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.Globals;

@OnApplicationStart
public class Initialize extends Job<String> {

    @Override
    public void doJob() throws Exception {
        if (User.count() > 0) return;
        Logger.info("Data Initialize");
        // 初始化超级管理员，超级管理员可以访问所有资源
        UserGroup admin = new UserGroup();
        admin.name = Globals.ADMIN;
        admin.save();
        User root = new User("root@lt.com", "root");
        root.nickName = "Root";
        root.userGroup = admin;
        root.save();
        // 初始化匿名用户组
        UserGroup anonymous = new UserGroup();
        anonymous.name = Globals.ANONYMOUS;
        anonymous.save();
        initAnonymous(anonymous);
        // 初始化普通用户组，新注册用户默认都是该用户组
        UserGroup normalUser = new UserGroup();
        normalUser.name = Globals.NORMAL_USER;
        normalUser.save();
        initNormalUser(normalUser);

        User u = new User("admin@admin.com", "admin");
        u.userGroup = normalUser;
        u.save();
    }

    /**
     * 初始化匿名用户的权限
     */
    private void initAnonymous(UserGroup anonymous) {
        anonymous.resources.add(new Resource("Application.index", "Application.index"));
        // Users
        anonymous.resources.add(Resource.createOrFetch("Users.register", "Users.register"));
        anonymous.resources.add(Resource.createOrFetch("Users.add", "Users.add"));
        anonymous.resources.add(Resource.createOrFetch("Users.checkEmail", "Users.checkEmail"));
        anonymous.resources.add(Resource.createOrFetch("Users.login", "Users.login"));
        anonymous.resources.add(Resource.createOrFetch("Users.logout", "Users.logout"));
        anonymous.resources.add(Resource.createOrFetch("Users.loginProcess", "Users.loginProcess"));
        anonymous.save();
    }

    /**
     * 初始化普通用户的权限
     */
    private void initNormalUser(UserGroup normalUser) {
        normalUser.resources.add(Resource.createOrFetch("Users.logout", "Users.logout"));
        normalUser.resources.add(Resource.createOrFetch("Users.home", "Users.home"));
        normalUser.save();
    }
}
