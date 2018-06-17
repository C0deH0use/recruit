import { Component, OnInit }    from '@angular/core';
import { ActivatedRoute, Data } from "@angular/router";
import { QuestionService }      from "../../shared/services/question.service";

@Component ({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: [ './question.component.scss' ]
})
export class QuestionComponent implements OnInit {
  isNew: boolean;
  withAnswer: boolean;

  constructor (private activeRoute: ActivatedRoute, private questionService: QuestionService) {
    this.activeRoute.data.subscribe ((routeData: Data) => {
      this.isNew = !!routeData.isNew;
      this.withAnswer = routeData.questionType === 'CandidateQuestion';
    })
  }

  ngOnInit () {
  }

}
