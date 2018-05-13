import { Routes } from '@angular/router';

//Route for content layout with sidebar, navbar and footer
export const routes: Routes = [
  {
    path: 'changelog',
    loadChildren: './changelog/changelog.module#ChangeLogModule'
  },
  {
    path: 'questionnaires',
    loadChildren: './questionnaires/questionnaires.module#QuestionnairesModule'
  },
  {
    path: 'full-layout',
    loadChildren: './pages/full-layout-page/full-pages.module#FullPagesModule'
  }
];
