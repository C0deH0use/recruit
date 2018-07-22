import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router }                      from '@angular/router';

import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { DataSource }                                from '@angular/cdk/collections'
import { Observable }                                from "rxjs/Observable";

import { QuestionnaireService } from '../shared/services/questionnaire.service';
import { Questionnaire }        from "../shared/questionnaries/questionnaire.modal";


@Component({
  selector: 'questionnaires',
  templateUrl: './questionnaires.component.html',
  styleUrls: ['./questionnaires.component.scss']
})
export class QuestionnairesComponent implements OnInit, AfterViewInit {
  @ViewChild (MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  pageSizeOptions: number[] = [5, 10, 25, 100];
  displayedColumns: string[] = ['action', 'id', 'user', 'createdDate', 'additionalNotes'];

  dataSource: MatTableDataSource<Questionnaire>;


  constructor(private questionnaireService: QuestionnaireService, private router: Router, private activeRoute: ActivatedRoute) {}

  ngOnInit() {
    this.questionnaireService.getQuestionnaires()
      .subscribe(questionnaries => {
        this.dataSource = new MatTableDataSource<Questionnaire>(questionnaries);
      });
  }

  ngAfterViewInit () {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  onNewQuestionnaire() {
    this.router.navigate(['new'], {relativeTo: this.activeRoute});
  }

  onNavigateToDetails(questionnaireId: string) {
    this.router.navigate(['by-id', questionnaireId], {relativeTo: this.activeRoute});
  }
}
