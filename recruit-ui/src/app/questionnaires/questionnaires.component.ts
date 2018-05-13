import { Component, OnInit } from '@angular/core';
import { ActiveRoute } from '@angular/router';

import { QuestionnaireService } from '../shared/services/questionnaire.service';

@Component({
  selector: 'questionnaires',
  templateUrl: './questionnaires.component.html',
  styleUrls: ['./questionnaires.component.scss']
})
export class QuestionnairesComponent implements OnInit {

  constructor(private QuestionnaireService questionnaireService, private ActiveRoute activeRoute) { }

  ngOnInit() {

  }

}
