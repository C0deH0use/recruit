import { NgModule }     from '@angular/core';
import { FormsModule }  from "@angular/forms"
import { CommonModule } from '@angular/common';

import { QuestionsComponent } from './questions.component';
import { QuestionComponent }  from './question/question.component';

import { MatchHeightModule } from "../shared/directives/match-height.directive";
import { MaterialModule }    from "../shared/material/material.module";
import { NgbModule }         from "@ng-bootstrap/ng-bootstrap";

@NgModule ({
  imports: [
    CommonModule,
    FormsModule,
    MatchHeightModule,
    MaterialModule,
    NgbModule
  ],
  declarations: [ QuestionsComponent, QuestionComponent ],
  exports: [ QuestionsComponent ]
})
export class QuestionsModule {
}
