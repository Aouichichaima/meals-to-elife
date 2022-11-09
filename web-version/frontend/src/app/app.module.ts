import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { ClientComponent } from './client/client.component';
import { AdminComponent } from './admin/admin.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { GestionRestaurantComponent } from './gestion-restaurant/gestion-restaurant.component';
import { FooterComponent } from './footer/footer.component';
import { UpdateComponent } from './shared/update/update.component';
import { FormsModule } from '@angular/forms';
import { ListRestaurantsComponent } from './client/list-restaurants/list-restaurants.component';
import { ListMenuComponent } from './client/list-menu/list-menu.component';
import { HomeComponent } from './home/home.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FooterHomeComponent } from './footer-home/footer-home.component';
import { NavAdminComponent } from './nav-admin/nav-admin.component';
import { NavHomeComponent } from './nav-home/nav-home.component';
import { ProfileSettingsComponent } from './profile-settings/profile-settings.component';
import { SendMailComponent } from './send-mail/send-mail.component';
import { UserGestionComponent } from './user-gestion/user-gestion.component';
import { DeliveryStaffComponent } from './restaurant/delivery-staff/delivery-staff.component';
import { GestionComponent } from './restaurant/gestion/gestion.component';
import { DepotComponent } from './restaurant/depot/depot.component';
import { FeedbackDeliveryStaffComponent } from './restaurant/feedback-delivery-staff/feedback-delivery-staff.component';
import { CartComponent } from './client/cart/cart.component';
import { ProductComponent } from './restaurant/depot/list-products/product/product.component';
import { ListProductsComponent } from './restaurant/depot/list-products/list-products.component';
import { NewStockComponent } from './restaurant/depot/new-stock/new-stock.component';
import { NewProductComponent } from './restaurant/depot/new-product/new-product.component';
import { DepotStartComponent } from './restaurant/depot/depot-start/depot-start.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SigninComponent,
    SignupComponent,
    ClientComponent,
    AdminComponent,
    RestaurantComponent,
    GestionRestaurantComponent,
    FooterComponent,
    UpdateComponent,
    ListRestaurantsComponent,
    ListMenuComponent,


    HomeComponent,
    InscriptionComponent,
    ConnexionComponent,
    NavAdminComponent,
    NavHomeComponent,
    FooterHomeComponent,
    ContactComponent,
    UserGestionComponent,
    AboutComponent,
    DashboardComponent,
    SendMailComponent,
    ProfileSettingsComponent,
    DeliveryStaffComponent,
    GestionComponent,
    DepotComponent,
    FeedbackDeliveryStaffComponent,
    ProductComponent,
    ListProductsComponent,
    NewStockComponent,
    NewProductComponent,
    DepotStartComponent,
    CartComponent,
    ProductComponent,
    ListProductsComponent,
    NewStockComponent,
    NewProductComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
