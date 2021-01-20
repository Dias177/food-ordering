package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.UserDao;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.util.PasswordHashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginLogic {

    private static final Logger logger = LogManager.getLogger();
    private static final EntityTransaction transaction = new EntityTransaction();
    private static final UserDao userDao = new UserDao();

    private LoginLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static boolean checkLogin(String login, String password) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        String hashedPassword = PasswordHashing.hash(password);
        User user = new User();
        transaction.init(userDao);
        try {
            user = userDao.findUserByEmailAndPassword(login, hashedPassword);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            transaction.end();
        }
        return user.getEmail() != null || user.getRoleId() != 0;
    }

    public static String getUserRoleName(String login, String password) throws DaoException {
        String hashedPassword = PasswordHashing.hash(password);
        User user = new User();
        transaction.init(userDao);
        try {
            user = userDao.findUserByEmailAndPassword(login, hashedPassword);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            transaction.end();
        }
        return RoleLogic.getName(user.getRoleId());
    }

    public static long getUserId(String login) {
        User user = new User();
        transaction.init(userDao);
        try {
            user = userDao.findUserByEmail(login);
        } catch (DaoException e) {
            logger.error(e);
        } finally {
            transaction.end();
        }
        return user.getId();
    }
}
