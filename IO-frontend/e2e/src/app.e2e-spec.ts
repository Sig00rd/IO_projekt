import { AppPage } from './app.po';
import {browser} from 'protractor';

describe('znajdź meczora app', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    page.navigateTo();
  });

  it('should display correct title', () => {
    expect(page.getTitleText()).toEqual('Meczyki');
  });

  it('should show register link', () => {
    expect(page.getRegisterLink().getText()).toEqual('Rejestracja');
  });

  it('should show login link', () => {
    expect(page.getLoginLink().getText()).toEqual('Login');
  });

  it('should show login window after clicking login', () => {
    page.getLoginLink().click();
    browser.pause();
    page.getLoginWindow();
  });

  it('should show register window after clicking register', () => {
    page.getRegisterLink().click();
    browser.pause();
    page.getRegisterWindow();
  });

  it('should show register button after clicking register', () => {
    page.getRegisterLink().click();
    browser.pause();
    expect(page.getRegisterButton().getText()).toEqual('Zarejestruj');
  });

  it('should show login button after clicking register', () => {
    page.getLoginLink().click();
    browser.pause();
    expect(page.getLoginButton().getText()).toEqual('Login');
  });

  it('should show username and email fields on register form', () => {
    page.getRegisterLink().click();
    browser.pause();
    page.getUsernameField();
    page.getEmailField();
  });

  it('should show password and retype password fields on register form', () => {
    page.getRegisterLink().click();
    browser.pause();
    page.getPasswordField();
    page.getRePasswordField();
  });

  it('should show username and password fields on login form', () => {
    page.getLoginLink().click();
    browser.pause();
    page.getUsernameField();
    page.getPasswordField();
  });

  it('should show user navbar after entering valid credentials and logging in', () => {
    page.getLoginLink().click();
    browser.pause();
    page.getUsernameField().sendKeys('julo');
    page.getPasswordField().sendKeys('julo');
    expect(page.getLoggedNavbar);
  });
});
