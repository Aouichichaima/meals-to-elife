import { DeliveryDetailsComponent } from './restaurant/delivery-details/delivery-details.component';
import { ListDeliveryComponent } from './restaurant/list-delivery/list-delivery.component';
import { FeedbackDeliveryStaffComponent } from './restaurant/feedback-delivery-staff/feedback-delivery-staff.component';
import { DeliveryStaffComponent } from './restaurant/delivery-staff/delivery-staff.component';
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
import { NewStockComponent } from './restaurant/depot/new-stock/new-stock.component';
import { DepotStartComponent } from './restaurant/depot/depot-start/depot-start.component';
import { ListProductsComponent } from './restaurant/depot/list-products/list-products.component';
import { NewProductComponent } from './restaurant/depot/new-product/new-product.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  // {path:'signup', component: SignupComponent},
  {path:'client', component: ClientComponent},
  {path:'admin', component: AdminComponent},
  {path:'restaurant', component: RestaurantComponent},
  {path:'restaurant/gestion', component: GestionComponent},
  /**
   * @author djebby <djebby.firas@gmail.com>
   */
  {
    path:'restaurant/depot', component: DepotComponent,
    children: [
      { path: '', component: DepotStartComponent},
      { path: 'stock/:id', component: ListProductsComponent},
      { path: 'edit-stock/:id', component: NewStockComponent},
      { path: 'new-stock', component: NewStockComponent},

      {path: 'new-product', component: NewProductComponent},
      {path: 'edit-product/:id', component: NewProductComponent},
    ]
  },

  {path:'home', component: HomeComponent},
  {path:'inscription', component:InscriptionComponent},
  {path:'connexion', component:ConnexionComponent},
  {path:'apropos', component:AboutComponent},
  {path:'contact', component:ContactComponent},
  {path:'gestion', component:UserGestionComponent},
  {path:'dashboard', component:DashboardComponent},
  {path:'send', component:SendMailComponent},
  {path:'profile',component:ProfileSettingsComponent} ,
  {path:'restaurant/delivery-staff' ,component:DeliveryStaffComponent},
  {path :'restaurant/feedback-delivery-staff', component:FeedbackDeliveryStaffComponent},
  {path:'profile',component:ProfileSettingsComponent},
  {path:'client/cart',component:CartComponent},
  {path:'cart',component:CartComponent},
  {path:'profile',component:ProfileSettingsComponent} ,
  { path:'restaurant/delivery-staff' ,component:DeliveryStaffComponent},
  {path :'restaurant/feedback-delivery-staff',component:FeedbackDeliveryStaffComponent},
  {path : 'restaurant/delivery-details' , component:DeliveryDetailsComponent},
  {path : 'restaurant/list-delivery' , component:ListDeliveryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
