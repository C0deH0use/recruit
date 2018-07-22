import { FormsModule }         from "@angular/forms";
import { MaterialModule }      from "../shared/material/material.module";
import { NgModule }            from "@angular/core";
import { CommonModule }        from "@angular/common";
import { MatchHeightModule }   from "../shared/directives/match-height.directive";
import { CandidatesComponent } from "./candidates.component";

@NgModule ({
  imports: [
    FormsModule,
    CommonModule,
    MaterialModule,
    MatchHeightModule
  ],
  declarations: [
    CandidatesComponent
  ]
})
export class CandidatesModule {
}
