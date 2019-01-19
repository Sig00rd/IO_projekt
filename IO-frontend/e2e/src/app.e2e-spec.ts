import { AppPage } from './app.po';
import {browser} from 'protractor';

describe('welcome page', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    page.navigateTo();
  });

  it('should display correct title', () => {
    expect(page.getTitle().getText()).toEqual('Meczyki');
  });

  it('should show register link', () => {
    expect(page.getRegisterLink().getText()).toEqual('Rejestracja');
  });

  it('should show login link', () => {
    expect(page.getLoginLink().getText()).toEqual('Login');
  });
});

describe('after clicking login', () => {
    let page: AppPage;

    beforeEach( () => {
      page = new AppPage();
      page.navigateTo();
      page.getLoginLink().click();
      browser.pause();
    });


  it('should show login window', () => {
    page.getLoginLink().click();
    browser.pause();
    page.getLoginWindow();
  });

  it('should show username and password fields', () => {
    page.getLoginLink().click();
    browser.pause();
    page.getUsernameField();
    page.getPasswordField();
  });

  it('should show login button', () => {
    page.getLoginLink().click();
    browser.pause();
    expect(page.getLoginButton().getText()).toEqual('Login');
  });
});

describe('after clicking register', () => {
  let page: AppPage;

  beforeEach( () => {
    page = new AppPage();
    page.navigateTo();
    page.getRegisterLink().click();
    browser.pause();
  });


  it('should show register window', () => {
    page.getRegisterWindow();
  });

  it('should show register button', () => {
    expect(page.getRegisterButton().getText()).toEqual('Zarejestruj');
  });

  it('should show username and email fields', () => {
    page.getUsernameField();
    page.getEmailField();
  });

  it('should show password and retype password', () => {
    page.getPasswordField();
    page.getRePasswordField();
  });
});

describe('after entering valid credentials in login form', () => {
  let page: AppPage;
  const username = 'julo';
  const password = 'julo';

  beforeEach( () => {
    page = new AppPage();
    page.login(username, password);
  });

  afterEach( () => {
    browser.executeScript('window.sessionStorage.clear();');
  });

  it('should show user navbar after entering valid credentials and logging in', () => {
    expect(page.getLoggedNavbar());
  });

  it('should show arrange match button', () => {
    expect(page.getArrangeMatchButton());
  });

  it('should show find match button', () => {
    expect(page.getFindMatchButton());
  });

  it('should show correct username on the navbar', () => {
    expect(page.getUserProfileLink(username));
  });

  it('should More button', () => {
    page.getMoreButton().isPresent();
  });

  it('show Account settings and Logout buttons after clicking More button', () => {
    page.getMoreButton().click();
    browser.pause();
    page.getAccountSettingsButton();
    page.getLogoutButton();
  });

  it('should log out after clicking logout', () => {
    page.getMoreButton().click();
    browser.pause();
    page.getLogoutButton().click();
    browser.pause();
    page.getTitle().click();
    expect(page.getArrangeMatchButton().isPresent()).toBeFalsy();
  });
});

  describe('arrange match screen', () => {
    let page: AppPage;
    const username = 'julo';
    const password = 'julo';

    beforeEach( () => {
      page = new AppPage();
      page.login(username, password);
    });
  });

  describe('find match screen', () => {
    let page: AppPage;
    const username = 'julo';
    const password = 'julo';

    beforeEach( () => {
      page = new AppPage();
      page.login(username, password);
    });
  });
