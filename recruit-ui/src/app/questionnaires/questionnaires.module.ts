import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { QuestionnairesRouts } from "./questionnaires-routing.module";
import { MatchHeightModule } from "../shared/directives/match-height.directive";

import { QuestionnairesComponent } from "./questionnaires.component";

@NgModule({
  imports: [
    CommonModule,
    MatchHeightModule,
    QuestionnairesRouts
  ],
  declarations: [QuestionnairesComponent]
})
export class QuestionnairesModule { }
