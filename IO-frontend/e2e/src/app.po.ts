import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  getTitleText() {
    return element(by.css('a.navbar-brand')).getText();
  }

  getRegisterLink() {
    return element(by.linkText('Rejestracja'));
  }

  getLoginLink() {
    return element(by.linkText('Login'));
  }

  getLoginWindow() {
    return element(by.css('login'));
  }

  getRegisterWindow() {
    return element(by.css('register'));
  }

  getRegisterButton() {
    return element(by.css('button.btn-primary'));
  }

  getLoginButton() {
    return element(by.css('button.btn-primary'));
  }
}
