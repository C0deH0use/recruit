import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router }                      from '@angular/router';

import { MatSort, MatTableDataSource } from '@angular/material';
import { DataSource }                  from '@angular/cdk/collections'
import { Observable }                  from "rxjs/Observable";

import { QuestionnaireService } from '../shared/services/questionnaire.service';
import { Questionnaire }        from "../shared/questionnaries/questionnaire.modal";


@Component({
  selector: 'questionnaires',
  templateUrl: './questionnaires.component.html',
  styleUrls: ['./questionnaires.component.scss']
})
export class QuestionnairesComponent implements OnInit, AfterViewInit {
  @ViewChild (MatSort) sort: MatSort;
  displayedColumns: string[];
  dataSource: MatTableDataSource<Questionnaire>;


  constructor(private questionnaireService: QuestionnaireService, private router: Router, private activeRoute: ActivatedRoute) {
    this.displayedColumns = ['action', 'id', 'user', 'createdDate', 'additionalNotes'];
  }

  ngOnInit() {
    this.questionnaireService.getQuestionnaires()
      .subscribe(questionnaries => {
        this.dataSource = new MatTableDataSource<Questionnaire>(questionnaries);
      });
  }

  ngAfterViewInit () {
    this.dataSource.sort = this.sort;
  }

  onNewQuestionnaire() {
    this.router.navigate(['new'], {relativeTo: this.activeRoute});
  }

  onNavigateToDetails(questionnaireId: string) {
    this.router.navigate([questionnaireId], {relativeTo: this.activeRoute});
  }
}
