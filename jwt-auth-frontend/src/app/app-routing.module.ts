import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { BoardStaffComponent } from './board-staff/board-staff.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardManagerComponent } from './board-manager/board-manager.component';
import { CategoryComponent } from './category/category.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'login', component: LoginComponent},
  {path: 'board-admin', component: BoardAdminComponent},
  {path: 'board-staff', component: BoardStaffComponent},
  {path: 'board-manager', component: BoardManagerComponent},
  {path: 'category', component: CategoryComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
