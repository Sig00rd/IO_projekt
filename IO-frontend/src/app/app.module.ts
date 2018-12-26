import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

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
import {HttpClientModule} from '@angular/common/http';
import { GameDetailsComponent } from './forms/find-game-panel/game-details/game-details.component';
import { GameListComponent } from './forms/find-game-panel/game-list/game-list.component';
import { GameElementComponent } from './forms/find-game-panel/game-list/game-element/game-element.component';

// TODO add routing

@NgModule({
  declarations: [
    AppComponent,
    LoginPanelComponent,
    LoginPageComponent,
    RegisterPageComponent,
    LoggedNavbarComponent,
    SettingsDirective,
    FeatureChoicePanelComponent,
    HostGamePanelComponent,
    FindGamePanelComponent,
    GameDetailsComponent,
    GameListComponent,
    GameElementComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
