package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.UserDao;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.util.PasswordHashing;

import java.sql.Date;
import java.util.List;

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

    public static boolean editInfo(long id, String firstName, String lastName, Date birthday, String phoneNumber,
                                       String email) throws DaoException {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setBirthday(birthday);
        int rows;
        transaction.initTransaction(userDao);
        try {
            rows = userDao.update(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return rows != 0;
    }

    public static boolean emailExists(long id, String email) throws DaoException {
        User user;
        transaction.init(userDao);
        try {
            user = userDao.findUserByEmail(email);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return user.getEmail() != null && id != user.getId();
    }

    public static boolean editPassword(long id, String password) throws DaoException {
        User user = new User();
        user.setId(id);
        user.setPassword(PasswordHashing.hash(password));
        int rows;
        transaction.initTransaction(userDao);
        try {
            rows = userDao.updatePassword(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return rows != 0;
    }

    public static List<User> getAllCustomers() throws DaoException {
        List<User> customers;
        transaction.init(userDao);
        try {
            customers = userDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return customers;
    }

    public static boolean remove(long userId) throws DaoException {
        boolean isRemoved;
        transaction.initTransaction(userDao);
        try {
            isRemoved = userDao.deleteEntityById(userId);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return isRemoved;
    }
}
