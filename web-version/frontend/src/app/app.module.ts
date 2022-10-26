import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { ClientComponent } from './client/client.component';
import { AdminComponent } from './admin/admin.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { GestionRestaurantComponent } from './gestion-restaurant/gestion-restaurant.component';
import { ListRestaurantsComponent } from './client/list-restaurants/list-restaurants.component';
import { ListMenuComponent } from './client/list-menu/list-menu.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    ClientComponent,
    AdminComponent,
    RestaurantComponent,
    GestionRestaurantComponent,
    ListRestaurantsComponent,
    ListMenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
