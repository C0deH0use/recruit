import { Routes } from '@angular/router';
import { AddEditQuestionnaireComponent } from "../../questionnaires/questionnaire/add-edit-questionnaire.component";
import { QuestionnairesComponent } from "../../questionnaires/questionnaires.component";


//Route for content layout with sidebar, navbar and footer
export const routes: Routes = [
  {
    path: 'changelog', loadChildren: './changelog/changelog.module#ChangeLogModule'
  },
  {
    path: 'questionnaires', children: [
      { path: '', component: QuestionnairesComponent },
      { path: 'id/:id',  component: AddEditQuestionnaireComponent },
      { path: 'new', data: { isNew: true },  component: AddEditQuestionnaireComponent },

    ]
  },
  {
    path: 'full-layout', loadChildren: './pages/full-layout-page/full-pages.module#FullPagesModule'
  }
];
