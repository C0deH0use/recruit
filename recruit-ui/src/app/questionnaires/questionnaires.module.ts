import { NgModule }     from '@angular/core';
import { CommonModule } from "@angular/common";
import { FormsModule }  from "@angular/forms"

import { MatchHeightModule } from "../shared/directives/match-height.directive";

import { QuestionnairesComponent }       from "./questionnaires.component";
import { AddEditQuestionnaireComponent } from "./questionnaire/add-edit-questionnaire.component";
import { QuestionsModule }               from "../questions/questions.module";
import { MaterialModule }                from "../shared/material/material.module";

@NgModule ({
  imports: [
    FormsModule,
    CommonModule,
    MaterialModule,
    MatchHeightModule,
    QuestionsModule
  ],
  declarations: [
    QuestionnairesComponent,
    AddEditQuestionnaireComponent
  ]
})
export class QuestionnairesModule {
}
