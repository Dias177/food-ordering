package kz.epam.tcfp.foodordering.util;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.RoleLogic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserCardBodyTag extends TagSupport {

    private static final String DELETE = "delete";
    private static final String DIV_CLASS_CARD_BODY = "<div class='card-body'>";
    private static final String H5_CLASS_CARD_TITLE = "<h5 class='card-title'>";
    private static final String P_CLASS_CARD_TEXT = "<p class='card-text'>";
    private static final String A_TAG_WITH_PARAMS = "<a href=%s/controller?command=delete_user&user_id=%d " +
            "class='btn btn-danger' role='button'>";
    private static final String DIV_CLOSING = "</div>";
    private static final String H5_CLOSING = "</h5>";
    private static final String P_CLOSING = "</p>";
    private static final String A_CLOSING = "</a>";

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
            String roleName = RoleLogic.getName(user.getRoleId());
            JspWriter out = pageContext.getOut();
            out.write(DIV_CLASS_CARD_BODY);
            out.write( H5_CLASS_CARD_TITLE + user.getFirstName() + " " + user.getLastName() + H5_CLOSING);
            out.write(P_CLASS_CARD_TEXT + user.getBirthday() + P_CLOSING);
            out.write(P_CLASS_CARD_TEXT + user.getPhoneNumber() + P_CLOSING);
            out.write(P_CLASS_CARD_TEXT + user.getEmail() + P_CLOSING);
            out.write(P_CLASS_CARD_TEXT + roleName + P_CLOSING);
            if (DELETE.equalsIgnoreCase(type)) {
                out.write(String.format(A_TAG_WITH_PARAMS, pageContext.getServletContext().getContextPath(), user.getId()));
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
                pageContext.getOut().write(A_CLOSING);
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write(DIV_CLOSING);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
