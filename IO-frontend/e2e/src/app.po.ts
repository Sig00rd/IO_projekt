import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  private getFormField(fieldname) {
    const fieldcss = 'input[formcontrolname=' + fieldname + ']';
    return element(by.css(fieldcss));
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
}
