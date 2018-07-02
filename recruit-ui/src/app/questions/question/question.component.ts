import { Component, OnInit }                    from '@angular/core';
import { ActivatedRoute, Data, Params, Router } from "@angular/router";
import 'rxjs/add/operator/filter';


import { QuestionService }      from "../../shared/services/question.service";
import { QuestionnaireService } from "../../shared/services/questionnaire.service";
import { Question }             from "../../shared/questions/question.modal";
import { Dificulty }            from "../../shared/questions/dificulty.modal";
import { Category }             from "../../shared/questions/category.modal";

@Component ({
  selector: 'question',
  templateUrl: './question.component.html',
  styleUrls: [ './question.component.scss' ]
})
export class QuestionComponent implements OnInit {
  isNew: boolean;
  withAnswer: boolean;
  questionId: string;
  questionnaireId: string;
  model: Question;
  difficulties: string[];
  categories: string[];


  constructor (private activeRoute: ActivatedRoute, private router: Router, private questionService: QuestionService, private questionnaireService: QuestionnaireService) {
  }

  ngOnInit () {
    this.difficulties = Object.keys (Dificulty);
    let categoryKeys = Object.keys (Category);
    this.categories = categoryKeys.slice (categoryKeys.length, categoryKeys.length - 1);

    this.activeRoute.data.subscribe ((routeData: Data) => {
      this.isNew = !!routeData.isNew;
      this.withAnswer = routeData.questionType === 'CandidateQuestion';

      this.activeRoute.params.subscribe ((params: Params) => {
        this.questionId = params[ "id" ];

        if (this.questionId != null) {
          this.questionService.findQuestionById (this.questionId)
            .subscribe ((question: Question) => this.model = question);
        } else {
          this.model = new Question ("", "", "", null, null);
        }

      });
      this.questionnaireId = this.activeRoute.snapshot.queryParams[ "questionnaireId" ];
    });
  }

  onCancel () {
    if (this.questionnaireId) {
      this.router.navigate ([ "questionnaire", this.questionnaireId ], { relativeTo: null })
    } else {
      this.router.navigate ([ "questionnaire" ], { relativeTo: null })
    }
  }

  onSave () {

  }
}
