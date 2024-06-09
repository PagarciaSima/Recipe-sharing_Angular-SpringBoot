import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthComponent } from './pages/auth/auth.component';
import { AuthServiceService } from './services/auth/auth-service.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, 
    CommonModule,
    NavbarComponent,
    FooterComponent,
    HomePageComponent,
    AuthComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'recipe-sharing';

  user: any = null;

  constructor(public authService: AuthServiceService){

  }
  ngOnInit(){
    this.authService.getUserProfile().subscribe();
    this.authService.authSubject.subscribe(
      (auth) => {
        console.log("auth state ", auth);
        this.user = auth.user;
      }
    )
  }
}
