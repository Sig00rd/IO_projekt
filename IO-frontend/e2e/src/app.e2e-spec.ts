import { AppPage } from './app.po';
import {browser} from 'protractor';

describe('meczyki app', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display correct title', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Meczyki');
  });

  it('should show register link', () => {
    page.navigateTo();
    expect(page.getRegisterLink().getText()).toEqual('Rejestracja');
  });

  it('should show login link', () => {
    page.navigateTo();
    expect(page.getLoginLink().getText()).toEqual('Login');
  });

  it('should show login window after clicking login', () => {
    page.navigateTo();
    page.getLoginLink().click();
    browser.pause();
    page.getLoginWindow();
  });

  it('should show register window after clicking register', () => {
    page.navigateTo();
    page.getRegisterLink().click();
    browser.pause();
    page.getRegisterWindow();
  });

  it('should show register button after clicking register', () => {
    page.navigateTo();
    page.getRegisterLink().click();
    browser.pause();
    expect(page.getRegisterButton().getText()).toEqual('Zarejestruj');
  });

  it('should show login button after clicking register', () => {
    page.navigateTo();
    page.getLoginLink().click();
    browser.pause();
    expect(page.getLoginButton().getText()).toEqual('Login');
  });
});
