import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { MatSort } from '@angular/material';
import { DataSource } from '@angular/cdk/collections'
import { Observable } from "rxjs/Observable";

import { QuestionnaireService } from '../shared/services/questionnaire.service';
import { Questionnaire } from "../shared/services/questionnaire.model";


@Component({
  selector: 'questionnaires',
  templateUrl: './questionnaires.component.html',
  styleUrls: ['./questionnaires.component.scss']
})
export class QuestionnairesComponent implements OnInit {
  displayedColumns: string[];
  dataSource: QuestionnairesDataSource;

  constructor(private questionnaireService: QuestionnaireService, private router: Router, private activeRoute: ActivatedRoute) {
    this.displayedColumns = ['id', 'user', 'createdDate', 'additionalNotes'];
  }

  ngOnInit() {
    this.dataSource = new QuestionnairesDataSource(this.questionnaireService);
  }

  onNewQuestionnaire() {
    this.router.navigate(['new'], {relativeTo: this.activeRoute});
  }
}

export class QuestionnairesDataSource extends DataSource<any> {
  constructor(private questionnaireService: QuestionnaireService) {
    super();
  }
  connect(): Observable<Questionnaire[]> {
    return this.questionnaireService.getQuestionnaires();
  }
  disconnect() {}
}
