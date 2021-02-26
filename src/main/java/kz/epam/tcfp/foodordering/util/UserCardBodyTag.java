package kz.epam.tcfp.foodordering.util;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Role;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.RoleLogic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class UserCardBodyTag extends TagSupport {

    private static final String P_CLASS_CARD_TEXT = "<p class='card-text'>";
    private static final String P_CLOSING = "</p>";
    private static final String DELETE = "delete";

    private User user;
    private String type;

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            List<Role> roles = RoleLogic.getAll();
            String roleName = "";
            for (Role role : roles) {
                if (role.getId() == user.getRoleId()) {
                    roleName = role.getName();
                    break;
                }
            }
            JspWriter out = pageContext.getOut();
            out.write("<div class='card-body'>");
            out.write("<h5 class='card-title'>" + user.getFirstName() + " " + user.getLastName() + "</h5>");
            out.write(P_CLASS_CARD_TEXT + user.getBirthday() + P_CLOSING);
            out.write(P_CLASS_CARD_TEXT + user.getPhoneNumber() + P_CLOSING);
            out.write(P_CLASS_CARD_TEXT + user.getEmail() + P_CLOSING);
            out.write(P_CLASS_CARD_TEXT + roleName + P_CLOSING);
            if (DELETE.equalsIgnoreCase(type)) {
                out.write("<a href='" + pageContext.getServletContext().getContextPath() +
                        "/controller?command=delete_user&user_id=" + user.getId() + "' class='btn btn-danger' role='button'>");
            }
        } catch (IOException | DaoException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            if (type != null) {
                pageContext.getOut().write("</a>");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</div>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
