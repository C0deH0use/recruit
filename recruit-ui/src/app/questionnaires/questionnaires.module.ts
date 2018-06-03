import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { MatchHeightModule } from "../shared/directives/match-height.directive";

import { QuestionnairesComponent } from "./questionnaires.component";
import { AddEditQuestionnaireComponent} from "./questionnaire/add-edit-questionnaire.component";
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card'

@NgModule({
  imports: [
    CommonModule,
    MatchHeightModule,
    MatTableModule,
    MatCardModule
  ],
  declarations: [QuestionnairesComponent,
    AddEditQuestionnaireComponent
  ]
})
export class QuestionnairesModule { }
