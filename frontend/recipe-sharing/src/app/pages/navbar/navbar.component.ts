import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AuthServiceService } from '../../services/auth/auth-service.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MatToolbarModule, 
    MatButtonModule, 
    MatIconModule,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  user: any = null;

  constructor(public authService: AuthServiceService){

  }
  ngOnInit(){
    this.authService.authSubject.subscribe(
      (auth) => {
        console.log("auth state ", auth);
        this.user = auth.user;
      }
    )
  }
}
