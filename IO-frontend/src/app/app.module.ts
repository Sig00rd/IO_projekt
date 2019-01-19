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
import { HostedGamesComponent } from './user/hosted-games/hosted-games.component';
import { JoinedGamesComponent } from './user/joined-games/joined-games.component';
import { GamesElementComponent } from './user/games-element/games-element.component';
import { GameNotExistingComponent } from './game-not-existing/game-not-existing.component';
import { MessagesComponent } from './navbars/logged-navbar/messages/messages.component';
import { SettingsComponent } from './navbars/logged-navbar/settings/settings.component';
import {AuthGuardService} from './services/authguard.service';
import { WelcomeComponent } from './welcome/welcome.component';
import {HomePageGuard} from './services/home.page.guard';

const appRoutes: Routes = [
  {path: '', canActivate: [HomePageGuard], component: WelcomeComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegisterPageComponent},
  {path: 'home', component: FeatureChoicePanelComponent, canActivate: [AuthGuardService]},
  {path: 'find', component: FindGamePanelComponent, canActivate: [AuthGuardService], children: [
      {path: '-1', component: GameNotExistingComponent},
      {path: ':id', component: GameDetailsComponent}
    ]},
  {path: 'host', component: HostGamePanelComponent, canActivate: [AuthGuardService]},
  {path: 'settings', component: SettingsComponent, canActivate: [AuthGuardService]},
  {path: 'messages', component: MessagesComponent, canActivate: [AuthGuardService]},
  {path: 'user', component: UserComponent, canActivate: [AuthGuardService]},
  {path: '**', redirectTo: ''}
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
    HostedGamesComponent,
    JoinedGamesComponent,
    GamesElementComponent,
    GameNotExistingComponent,
    MessagesComponent,
    SettingsComponent,
    WelcomeComponent
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
    AuthGuardService,
    HomePageGuard,
    {provide: LOCALE_ID, useValue: 'pl'},
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
