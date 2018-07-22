import { AfterViewInit, Component, Input, OnInit, ViewChild }           from '@angular/core';
import { MatPaginator, MatSort, MatSortHeaderIntl, MatTableDataSource } from "@angular/material";
import { CandidateQuestion }                                            from "../shared/questions/candidateQuestions.modal";
import { QuestionService }                                              from "../shared/services/question.service";
import { ActivatedRoute, Data, Router }                                 from "@angular/router";
import { Question }                                                     from "../shared/questions/question.modal";

@Component ({
  selector: 'questions',
  templateUrl: './questions.component.html',
  styleUrls: [ './questions.component.scss' ]
})
export class QuestionsComponent implements OnInit , AfterViewInit {
  @ViewChild (MatSort) sort: MatSort;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @Input () questionnaireId: string;
  @Input () candidateQuestions: CandidateQuestion[];

  private inQuestionnaire : boolean = false;
  dataSource: MatTableDataSource<CandidateQuestion> | MatTableDataSource<Question>;

  displayedColumns: string[] = [ 'question-edit', 'questionText', 'answer', 'difficulty', 'category' ];
  pageSizeOptions: number[] = [5, 10, 25, 100];

  constructor (private questionService: QuestionService, private router: Router,  private activeRoute: ActivatedRoute) {
  }

  ngOnInit () {
    if (this.candidateQuestions != null) {
      this.dataSource = new MatTableDataSource<CandidateQuestion> (this.candidateQuestions);
      this.displayedColumns.push ('candidateAnswer');
    } else {
      this.activeRoute.data.subscribe ((routeData: Data) => {
        this.inQuestionnaire = routeData.questionType === "CandidateQuestion";
        if (this.inQuestionnaire) {
          this.dataSource = new MatTableDataSource<CandidateQuestion> ();
        } else {
          this.questionService.getQuestions ()
            .subscribe (questions => {
              this.dataSource = new MatTableDataSource<Question> (questions);
            });
        }
      });
    }
  }

  /**
   * Set the sort after the view init since this component will
   * be able to query its view for the initialized sort.
   */
  ngAfterViewInit () {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  onEdit (id: string) {
    if (this.candidateQuestions != null) {
      this.router.navigate ([ "questions", "with-answer", id ], { queryParams: {questionnaireId: this.questionnaireId}, relativeTo: null });
    } else {
      this.router.navigate ([ "questions", "by-id", id ], { queryParams: {questionnaireId: this.questionnaireId}, relativeTo: null });
    }
  }

  onNew () {
    this.router.navigate(["questions", "new"], { queryParams: {questionnaireId: this.questionnaireId}, relativeTo: null });
  }
}
