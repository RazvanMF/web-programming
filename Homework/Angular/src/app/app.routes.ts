import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import {AdminComponent} from "./admin/admin.component";
import {ClientComponent} from "./client/client.component";

export const routes: Routes = [
    {path: '', redirectTo: '/login', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'admin', component: AdminComponent},
    {path: 'client', component: ClientComponent}
];
