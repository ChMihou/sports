package com.physical.movement.common;

import com.physical.movement.entity.SysUser;

import javax.servlet.http.HttpSession;

public final class LoginContext {
    public final static String USER_SESSION_KEY = "user_session_key";
    private final static ThreadLocal<User> userHolder = new ThreadLocal<User>() {
        @Override
        protected User initialValue() {
            return null;
        }
    };

    public static void clear() {
        userHolder.remove();
    }

    private static void set(User user) {
        userHolder.set(user);
    }

    public static User get() {
        return userHolder.get();
    }

    public static boolean isAdmin() {
        User user = userHolder.get();
        return user == null ? false : user.getSysUserType() == 1;
    }

    public static String getUsername() {
        User user = userHolder.get();
        return user == null ? null : user.username;
    }

    public static String getUsername(String defaultUsername) {
        User user = userHolder.get();
        return user == null ? defaultUsername : user.username;
    }

    public static Integer getUid() {
        User user = userHolder.get();
        return user == null ? null : user.uid;
    }

    public static boolean checkLogin(HttpSession session) {
        if (session == null)
            return false;
        User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (user == null)
            return false;
        else {
            set(user);
            return true;
        }
    }

    public static void doLogin(SysUser sysUser, HttpSession session) {
        if (sysUser.getId() == null || sysUser.getUsername() == null || sysUser.getUsertype() == null
                || session == null)
            return;
        User user = new User();
        user.uid = sysUser.getId();
        user.username = sysUser.getUsername();
        user.uuid = session.getId();
        user.status = sysUser.getStatus();
        session.setAttribute(USER_SESSION_KEY, user);
        set(user);
    }

    public final static class User {
        public Integer uid;
        public String username;
        public String privilege;
        public String uuid;
        public Integer refId;
        public String company;
        public Byte sysUserType;
        public Byte status;

        public Byte getSysUserType() {
            return sysUserType;
        }

        public void setSysUserType(Byte sysUserType) {
            this.sysUserType = sysUserType;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public Integer getRefId() {
            return refId;
        }

        public void setRefId(Integer refId) {
            this.refId = refId;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPrivilege() {
            return privilege;
        }

        public void setPrivilege(String privilege) {
            this.privilege = privilege;
        }

        public Byte getStatus() {
            return status;
        }

        public void setStatus(Byte status) {
            this.status = status;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}