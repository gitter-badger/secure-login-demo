package org.terasoluna.securelogin.app.welcome;

import javax.inject.Inject;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Inject
	AccountSharedService accountSharedService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String home(@AuthenticationPrincipal LoggedInUser userDetails, Model model) {

		Account account = userDetails.getAccount();

		model.addAttribute("account", account);
		model.addAttribute("isPasswordExpired", accountSharedService
				.isCurrentPasswordExpired(account.getUsername()));

		return "welcome/home";

	}

}
