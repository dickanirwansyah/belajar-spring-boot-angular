import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardStaffComponent } from './board-staff/board-staff.component';
import { BoardManagerComponent } from './board-manager/board-manager.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CategoryComponent } from './category/category.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    BoardAdminComponent,
    BoardStaffComponent,
    BoardManagerComponent,
    ProfileComponent,
    RegisterComponent,
    CategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
