package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.UserDao;
import kz.epam.tcfp.foodordering.entity.User;

public class ProfileLogic {

    private static final UserDao userDao = new UserDao();
    private static final EntityTransaction transaction = new EntityTransaction();

    private ProfileLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static User getUserById(long id) throws DaoException {
        User user;
        transaction.init(userDao);
        try {
            user = userDao.findEntityById(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return user;
    }
}
