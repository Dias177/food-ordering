package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.RoleDao;
import kz.epam.tcfp.foodordering.entity.Role;

import java.util.List;

public class RoleLogic {

    private static final EntityTransaction transaction = new EntityTransaction();
    private static final RoleDao roleDao = new RoleDao();

    private RoleLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static String getName(long roleId) throws DaoException {
        Role role;
        transaction.init(roleDao);
        try {
            role = roleDao.findEntityById(roleId);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return role.getName();
    }

    public static List<Role> getAll() throws DaoException {
        List<Role> roles;
        transaction.init(roleDao);
        try {
            roles = roleDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return roles;
    }

    public static boolean remove(long roleId) throws DaoException {
        boolean isRemoved;
        transaction.initTransaction(roleDao);
        try {
            isRemoved = roleDao.deleteEntityById(roleId);
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
