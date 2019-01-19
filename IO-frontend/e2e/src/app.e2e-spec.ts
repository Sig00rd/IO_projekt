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
    expect(page.getLoginWindow().isPresent()).toEqual(true);
  });

  it('should show username and password fields', () => {
    expect(page.getUsernameField().isPresent()).toEqual(true);
    expect(page.getPasswordField().isPresent()).toEqual(true);
  });

  it('should show login button', () => {
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
    expect(page.getRegisterWindow().isPresent()).toBeTruthy();
  });

  it('should show register button', () => {
    expect(page.getRegisterButton().getText()).toEqual('Zarejestruj');
  });

  it('should show username and email fields', () => {
    expect(page.getUsernameField().isPresent()).toEqual(true);
    expect(page.getEmailField().isPresent()).toEqual(true);
  });

  it('should show password and retype password', () => {
    expect(page.getPasswordField().isPresent()).toEqual(true);
    expect(page.getRePasswordField().isPresent()).toEqual(true);
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
    expect(page.getLoggedNavbar().isPresent()).toEqual(true);
  });

  it('should show arrange match button', () => {
    expect(page.getArrangeMatchButton().isPresent()).toEqual(true);
  });

  it('should show find match button', () => {
    expect(page.getFindMatchButton().isPresent()).toEqual(true);
  });

  it('should show correct username on the navbar', () => {
    expect(page.getUserProfileLink(username).isPresent()).toEqual(true);
  });

  it('should More button', () => {
    expect(page.getMoreButton().isPresent()).toEqual(true);
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
    expect(page.getArrangeMatchButton().isPresent()).toEqual(false);
  });
});

  describe('arrange match screen', () => {
    let page: AppPage;
    const username = 'julo';
    const password = 'julo';

    beforeEach( () => {
      page = new AppPage();
      page.login(username, password);
      browser.pause();
      page.getArrangeMatchButton().click();
      browser.pause();
    });

    afterEach( () => {
      page.getTitle().click();
      browser.pause();
      browser.executeScript('window.sessionStorage.clear();');
    });

    it('should show arrange match form', () => {
      expect(page.getArrangeMatchForm().isPresent()).toEqual(true);
    });

    it('should have add sport object by address and by name buttons', () => {
      expect(page.getAddByAddressButton().isPresent()).toEqual(true);
      expect(page.getAddByNameButton().isPresent()).toEqual(true);
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
