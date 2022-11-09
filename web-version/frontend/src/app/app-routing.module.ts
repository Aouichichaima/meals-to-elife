import { CartComponent } from './client/cart/cart.component';
import { GestionComponent } from './restaurant/gestion/gestion.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AdminComponent } from './admin/admin.component';
import { ClientComponent } from './client/client.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { ContactComponent } from './contact/contact.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GestionRestaurantComponent } from './gestion-restaurant/gestion-restaurant.component';
import { HomeComponent } from './home/home.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ProfileSettingsComponent } from './profile-settings/profile-settings.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { SendMailComponent } from './send-mail/send-mail.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { UserGestionComponent } from './user-gestion/user-gestion.component';
import { DepotComponent } from './restaurant/depot/depot.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  // {path:'signup', component: SignupComponent},
  {path:'client', component: ClientComponent},
  {path:'admin', component: AdminComponent},
  {path:'restaurant', component: RestaurantComponent},
  {path:'restaurant/gestion', component: GestionComponent},
  {path:'restaurant/depot', component: DepotComponent},

  {path:'home', component: HomeComponent},
  {path:'inscription', component:InscriptionComponent},
  {path:'connexion', component:ConnexionComponent},
  {path:'apropos', component:AboutComponent},
  {path:'contact', component:ContactComponent},
  {path:'gestion', component:UserGestionComponent},
  {path:'dashboard', component:DashboardComponent},
  {path:'send', component:SendMailComponent},
  {path:'profile',component:ProfileSettingsComponent},
  {path:'cart',component:CartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
