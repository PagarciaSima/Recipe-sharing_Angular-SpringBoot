import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { AuthServiceService } from '../../services/auth/auth-service.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, MatInputModule,MatFormFieldModule, FormsModule, MatButtonModule, ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {
  isRegister = false;

  constructor(public authService: AuthServiceService){
    
  }

  registrationForm = new FormGroup({
    fullName: new FormControl("", [Validators.required]),
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required, Validators.minLength(6)])
  });

  loginForm = new FormGroup({
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required])
  });

  handleRegister(){
    console.log("Registration ", this.registrationForm.value);
    this.authService.register(this.registrationForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt", response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("Registration success", response);
      }
    });
  }

  handleLogin(){
    console.log("Login ", this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next:(response)=>{
        localStorage.setItem("jwt", response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("login success", response);
      }
    });
  }

  togglePanel(){
    this.isRegister = !this.isRegister;
    
  }
}
