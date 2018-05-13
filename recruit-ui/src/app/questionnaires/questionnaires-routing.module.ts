import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import { QuestionnairesComponent } from "./questionnaires.component";

const routes: Routes = [
  {
    path: '',
    component: QuestionnairesComponent,
    data: {
      title: 'QuestionnairesRouts'
    },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class QuestionnairesRouts { }
