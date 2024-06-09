import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';
import { AuthServiceService } from '../../services/auth/auth-service.service';


@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RecipeCardComponent, MatIconModule, MatButtonModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent {
  recipes=[1,1,1,1,11,1]

  constructor(public dialog: MatDialog, public authService: AuthServiceService) {

  }

  handleOpenCreateRecipeFrom() {
    this.dialog.open(CreateRecipeFormComponent);
  }

  ngOnInit(){
    this.authService.getUserProfile();
  }
}
