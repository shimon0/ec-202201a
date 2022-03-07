package com.example.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ecommerce_a.domain.User;
import com.example.ecommerce_a.form.LoginForm;
import com.example.ecommerce_a.service.UserService;

/**
 * ログイン機能を操作するコントローラー
 * 
 * @author kashimamiyu
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	/**
	 * @return LoginFormをインスタンス化
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * @return ログイン画面を出力します。
	 */
	@RequestMapping("")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/complete")
	public String login(LoginForm form, Model model) {
		User user = userService.login(form.getEmail(), form.getPassword());
		if(user == null) {
			model.addAttribute("errorMessage", "メールアドレス、またはパスワードが間違っています");
			return toLogin();
		}
		session.setAttribute("userId", user.getId());
		return "item_list_coffee";
		
	}
}
