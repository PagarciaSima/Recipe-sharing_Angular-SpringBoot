import { Component, Inject } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { RecipeServiceService } from '../../services/recipe/recipe-service.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule,MatRadioModule],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.scss'
})
export class UpdateRecipeFormComponent {

  recipeItem: any = {
    title:"",
    description: "",
    foodType: "",
    image: ""
  }

  constructor(@Inject(MAT_DIALOG_DATA) public recipe: any, private recipeService: RecipeServiceService){

  }

  ngOnInit(){
    this.recipeItem = this.recipe;
  }

  onSubmit() {
    this.recipeService.updateRecipes(this.recipe.id).subscribe({
      next: data => console.log("Updated ", data),
      error:error => console.log("error ", error)
    });

  }

}
