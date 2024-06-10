import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { RecipeServiceService } from '../../services/recipe/recipe-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-recipe-form',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule,MatRadioModule],
  templateUrl: './create-recipe-form.component.html',
  styleUrl: './create-recipe-form.component.scss'
})
export class CreateRecipeFormComponent {

  recipeItem: any = {
    title:"",
    description: "",
    foodType: "",
    image: ""
  };

  constructor(private reciperService: RecipeServiceService, private router: Router){
 
  }

  onSubmit() {
    console.log("values ", this.recipeItem);
    this.reciperService.createRecipes(this.recipeItem).subscribe({
      next: data => {
        console.log("Created recipe ", data);
        window.location.reload(); 
      },
      error: error => console.log("Error ", error)
    });

  }
}
