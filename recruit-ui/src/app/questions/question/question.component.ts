import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Data, Router } from "@angular/router";
import { map }                          from 'rxjs/operators';
import { zip }                          from 'rxjs/observable/zip'


import { QuestionService }      from "../../shared/services/question.service";
import { QuestionnaireService } from "../../shared/services/questionnaire.service";
import { Question }             from "../../shared/questions/question.modal";
import { Dificulty }            from "../../shared/questions/dificulty.modal";
import { Category }             from "../../shared/questions/category.modal";
import { Questionnaire }        from "../../shared/questionnaries/questionnaire.modal";
import { CandidateQuestion }    from "../../shared/questions/candidateQuestions.modal";
import { MatPaginator }         from "@angular/material";

@Component ({
  selector: 'question',
  templateUrl: './question.component.html',
  styleUrls: [ './question.component.scss' ]
})
export class QuestionComponent implements OnInit {
  isNew: boolean = true;
  withAnswer: boolean = false;
  questionId: string;
  questionnaireId: string;
  model: Question | CandidateQuestion = new Question ("", "", "", Dificulty.JUNIOR, Category.JAVA_BASICS);
  difficulties: string[];
  categories: string[];


  constructor (private activeRoute: ActivatedRoute, private router: Router,
               private questionService: QuestionService, private questionnaireService: QuestionnaireService) {
  }

  ngOnInit () {
    this.difficulties = Object.keys (Dificulty).map (key => Dificulty[ key ]);
    this.categories = Object.keys (Category).map (key => Category[ key ]);

    this.activeRoute.data.subscribe ((routeData: Data) => {
      this.isNew = !!routeData.isNew;
      this.withAnswer = routeData.questionType === 'CandidateQuestion';

      zip (
        this.activeRoute.params.pipe (map (value => value[ "id" ])),
        this.activeRoute.queryParams.pipe (map (value => value[ "questionnaireId" ]))
      ).subscribe (params => {
        this.questionId = params[ 0 ];
        this.questionnaireId = params[ 1 ];

        if (this.withAnswer && this.questionnaireId != null) {
          this.questionnaireService.findById (this.questionnaireId)
            .subscribe ((questionnaire: Questionnaire) => this.model = questionnaire
              .candidateQuestions
              .find ((canQuestion: CandidateQuestion) => canQuestion.id === this.questionId)
            );
        }
        if (this.questionId != null) {
          this.questionService.findQuestionById (this.questionId)
            .subscribe ((question: Question) => this.model = question);
        }
      });
    });
    this.questionnaireId = this.activeRoute.snapshot.queryParams[ "questionnaireId" ];
  }


  onDifficultySet (newDifficulty: Dificulty) {
    this.model.difficulty = newDifficulty;
  }

  onCategorySet (newCat: Category) {
    this.model.category = newCat;
  }

  onCancel () {
    if (this.questionnaireId) {
      this.router.navigate ([ "questionnaires", this.questionnaireId ], { relativeTo: null })
    } else {
      this.router.navigate ([ "questionnaires" ], { relativeTo: null })
    }
  }

  onSave () {
    if (this.model instanceof Question) {
      this.questionService.save (this.model);
    }
    this.questionnaireService.save (this.questionnaireId, <CandidateQuestion> this.model);
    this.router.navigate ([ "questionnaires", this.questionnaireId ], { relativeTo: null });
  }
}
