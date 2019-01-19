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
    return element(by.id('login'));
  }

  getRegisterWindow() {
    return element(by.id('login'));
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
    return this.getFormField('rPassword');
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
    return element(by.buttonText('znaleźć ekipę'));
  }

  getArrangeMatchButton() {
    return element(by.buttonText('zebrać ekipę'));
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

  getArrangeMatchForm() {
    return element(by.id('host'));
  }

  getAddByAddressButton() {
    return element(by.buttonText('Dodaj po adresie'));
  }

  getAddByNameButton() {
    return element(by.buttonText('Dodaj po nazwie'));
  }
}
