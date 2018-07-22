import * as $ from 'jquery';


import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { SharedModule } from "./shared/shared.module";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { FullLayoutComponent } from "./layouts/full/full-layout.component";

import { AuthService }          from './shared/auth/auth.service';
import { AuthGuard }            from './shared/auth/auth-guard.service';
import { QuestionnaireService } from "./shared/services/questionnaire.service";
import { QuestionnairesModule } from "./questionnaires/questionnaires.module";
import { QuestionService }      from "./shared/services/question.service";
import { CandidatesService }    from "./shared/services/candidates.service";
import { CandidatesModule }     from "./candidates/candidates.module";

@NgModule({
    declarations: [
        AppComponent,
        FullLayoutComponent
    ],
    imports: [
        BrowserAnimationsModule,
        QuestionnairesModule,
        CandidatesModule,
        AppRoutingModule,
        SharedModule,
        NgbModule.forRoot()
    ],
    providers: [
        QuestionnaireService,
        QuestionService,
        CandidatesService,
        AuthService,
        AuthGuard
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
