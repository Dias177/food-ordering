package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.RoleDao;
import kz.epam.tcfp.foodordering.entity.Role;

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
}
