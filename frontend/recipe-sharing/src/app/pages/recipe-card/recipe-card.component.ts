import { Component } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import { UpdateRecipeFormComponent } from '../update-recipe-form/update-recipe-form.component';
import { Input } from '@angular/core';
import { RecipeServiceService } from '../../services/recipe/recipe-service.service';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [MatButtonModule, MatCardModule, MatIconModule],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss'
})
export class RecipeCardComponent {

  // Getting recipe from father home page
  @Input() recipe: any;

  constructor(public dialog: MatDialog, private recipeService: RecipeServiceService){

  }

  handleOpenEditRecipeForm() {
    this.dialog.open(UpdateRecipeFormComponent, {
      data: this.recipe
    });
  }

  handleDeleteRecipe(){
    this.recipeService.deleteRecipe(this.recipe.id).subscribe();
    window.location.reload(); 

  }
}
