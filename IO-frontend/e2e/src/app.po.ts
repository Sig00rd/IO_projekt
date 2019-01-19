import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  private getFormField(fieldname) {
    const fieldcss = 'input[formcontrolname=' + fieldname + ']';
    return element(by.css(fieldcss));
  }

  getTitle() {
    return element(by.css('a.navbar-brand'));
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

  getUsernameField() {
    return this.getFormField('username');
  }

  getEmailField() {
    return this.getFormField('email');
  }

  getPasswordField() {
    return this.getFormField('password');
  }

  getRePasswordField() {
    return this.getFormField('rpassword');
  }

  getLoggedNavbar() {
    return element(by.css('app-logged-navbar'));
  }

  getUserProfileLink(username) {
    return element(by.linkText(username));
  }

  getMoreButton() {
    return element(by.linkText('Więcej'));
  }

  getAccountSettingsButton() {
    return element(by.linkText('Ustawienia konta'));
  }

  getLogoutButton() {
    return element(by.linkText('Wyloguj'));
  }

  getFindMatchButton() {
    return element(by.css('btn-primary'));
  }

  getArrangeMatchButton() {
    return element(by.css('btn-success'));
  }

  login(username, password) {
    this.navigateTo();
    this.getLoginLink().click();
    browser.pause();
    this.getUsernameField().sendKeys(username);
    this.getPasswordField().sendKeys(password);
    this.getLoginButton().click();
    browser.pause();
  }
}
