package com.senac.elevcallsdefinitivo.controller;

import com.senac.elevcallsdefinitivo.entity.Login;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Glauber
 */
@ManagedBean(value = "LoginMB")
@ViewScoped
public class LoginManagedBean {

    private LoginDAO loginDAO = new LoginDAO();
    private Login login = new Login();

    public String envia() {

        login = loginDAO.getLogin(login.getUsuario(), login.getSenha());
        if (login == null) {
            login = new Login();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                            "Erro no login"));
            return null;
        } else {
            return "/chamadas/Create";
        }

    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
