import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import {registerLocaleData} from '@angular/common';
import localePl from '@angular/common/locales/pl';

import {AppComponent} from './app.component';
import {LoginPanelComponent} from './navbars/login-panel/login-panel.component';
import {LoginPageComponent} from './forms/login-page/login-page.component';
import {RegisterPageComponent} from './forms/register-page/register-page.component';
import {LoggedNavbarComponent} from './navbars/logged-navbar/logged-navbar.component';
import {SettingsDirective} from './shared/settings.directive';
import {FeatureChoicePanelComponent} from './feature-choice-panel/feature-choice-panel.component';
import {HostGamePanelComponent} from './forms/host-game-panel/host-game-panel.component';
import {FindGamePanelComponent} from './forms/find-game-panel/find-game-panel.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {GameDetailsComponent} from './forms/find-game-panel/game-details/game-details.component';
import {GameListComponent} from './forms/find-game-panel/game-list/game-list.component';
import {GameElementComponent} from './forms/find-game-panel/game-list/game-element/game-element.component';
import {SportsService} from './services/sports.service';
import {AgmCoreModule} from '@agm/core';
import {MapsService} from './services/maps.service';
import {RouterModule, Routes} from '@angular/router';
import {AuthInterceptor} from './auth/auth.interceptor';
import {TokenStorage} from './auth/token.storage';
import {FilterComponent} from './forms/find-game-panel/game-list/filter/filter.component';
import {UserComponent} from './user/user.component';
import {UserService} from './services/user.service';
import {NotificationDirective} from './shared/notification.directive';
import {GamesService} from './services/games.service';
import {SportObjectComponent} from './forms/sport-object/sport-object.component';

// TODO add routing details
// TODO add routing guards

const appRoutes: Routes = [
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegisterPageComponent},
  {path: 'home', component: FeatureChoicePanelComponent},
  {path: 'find', component: FindGamePanelComponent, children: [
      {path: ':id', component: GameDetailsComponent}
    ]},
  {path: 'host', component: HostGamePanelComponent},
  {path: 'user', component: UserComponent}
];
registerLocaleData(localePl);

@NgModule({
  declarations: [
    AppComponent,
    LoginPanelComponent,
    LoginPageComponent,
    RegisterPageComponent,
    LoggedNavbarComponent,
    SettingsDirective,
    NotificationDirective,
    FeatureChoicePanelComponent,
    HostGamePanelComponent,
    FindGamePanelComponent,
    GameDetailsComponent,
    GameListComponent,
    GameElementComponent,
    FilterComponent,
    UserComponent,
    SportObjectComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDTcMb9tsqsJSfWymNME76U1Zze1bORsSg'
    }),
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    HttpClientModule,
    SportsService,
    MapsService,
    GamesService,
    TokenStorage,
    UserService,
    {provide: LOCALE_ID, useValue: 'pl'},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
