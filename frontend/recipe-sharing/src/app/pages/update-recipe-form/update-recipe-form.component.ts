import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';

@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule,MatRadioModule],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.scss'
})
export class UpdateRecipeFormComponent {

  recipeItem: any = {
    title:"asas",
    description: "sss",
    foodType: "veg",
    image: "image"
  }

  onSubmit() {
    console.log("values ", this.recipeItem)
  }

}
