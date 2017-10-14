package beans;

import dao.UserDAO;
import models.Local;
import utils.DatabaseInit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


@Named
@SessionScoped
/*
 *
 * @author User
 */
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
    private Local selLocal;
    private List<Local> selLocals;


    public List<Local> getSelLocals() {
        Local localEn=new Local(1,"English","en.png");
        Local localRo=new Local(2,"Romana","ro.png");
        selLocals = new ArrayList<Local>();
        selLocals.add(localEn);
        selLocals.add(localRo);
        return selLocals;
    }
    public void setSelLocals(List<Local> selLocals) {
        this.selLocals = selLocals;
    }
    public Local getSelLocal() {
        return selLocal;
    }
    public void setSelLocal(Local selLocal) {
        this.selLocal = selLocal;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }

    public String loginProject() {
        DatabaseInit a = new DatabaseInit();
        a.createTables();
        boolean result = UserDAO.login(uname, password);
        if (result) {
            // get Http Session and store username
            HttpSession session = Util.getSession();
            session.setAttribute("username", uname);

            return "/protected/home";
        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));

            // invalidate session, and redirect to other pages

            //message = "Invalid Login. Please Try Again!";
            return "/public/login";
        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "/index";
    }
}
