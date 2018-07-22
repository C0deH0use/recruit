import { Component, OnInit, ViewChild } from "@angular/core";
import { ActivatedRoute, Data }         from "@angular/router";

import { Questionnaire }        from "../../shared/questionnaries/questionnaire.modal";
import { QuestionnaireService } from "../../shared/services/questionnaire.service";
import { CandidateQuestion }    from "../../shared/questions/candidateQuestions.modal";

@Component ({
  selector: 'add-edit-questionnaire',
  templateUrl: './add-edit-questionnaire.component.html',
  styleUrls: [ './add-edit-questionnaire.component.scss' ]
})
export class AddEditQuestionnaireComponent implements OnInit {
  @ViewChild (CandidateQuestion) questions;

  isNew: boolean;
  questionnaireId: string;
  modal: Questionnaire;


  constructor (private questionnaireService: QuestionnaireService, private activeRoute: ActivatedRoute) {
    this.activeRoute.data.subscribe ((routeData: Data) => {
      this.isNew = routeData.isNew;

      if (!this.isNew) {
        this.questionnaireId = this.activeRoute.snapshot.params[ "id" ];
      }
    });
  }

  ngOnInit () {
    if (this.isNew) {
      this.modal = Questionnaire.newOne ();
    } else {
      this.questionnaireService.findById (this.questionnaireId)
        .subscribe ((questionnaire: Questionnaire) => this.modal = questionnaire);
    }
  }
}


