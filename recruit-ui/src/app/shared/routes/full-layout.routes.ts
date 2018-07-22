import { Routes }                        from '@angular/router';
import { AddEditQuestionnaireComponent } from "../../questionnaires/questionnaire/add-edit-questionnaire.component";
import { QuestionnairesComponent }       from "../../questionnaires/questionnaires.component";
import { QuestionsComponent }            from "../../questions/questions.component";
import { QuestionComponent }             from "../../questions/question/question.component";
import { CandidatesComponent }           from "../../candidates/candidates.component";


//Route for content layout with sidebar, navbar and footer
export const routes: Routes = [
  {
    path: 'changelog', loadChildren: './changelog/changelog.module#ChangeLogModule'
  },
  {
    path: 'questionnaires', children: [
      { path: '', component: QuestionnairesComponent },
      { path: 'by-id/:id', data: { isNew: false }, component: AddEditQuestionnaireComponent },
      { path: 'new', data: { isNew: true },  component: AddEditQuestionnaireComponent },
    ]
  },
  {
    path: 'questions', children: [
      { path: '', component: QuestionsComponent },
      { path: 'by-id/:id', data: { questionType: "Question" }, component: QuestionComponent },
      { path: 'new', data: { questionType: "Question", isNew: true }, component: QuestionComponent },
      { path: 'with-answer/:id', data: { questionType: "CandidateQuestion" }, component: QuestionComponent },
    ]
  },
  {
    path: 'candidates', children: [
      { path: '', component: CandidatesComponent },
      // { path: 'new', component: CandidateComponent , data: {isNew: true}},
      // { path: 'by-id/:id', component: CandidateComponent , data: {isNew: false}}
    ]
  },
  {
    path: 'full-layout', loadChildren: './pages/full-layout-page/full-pages.module#FullPagesModule'
  }
];
