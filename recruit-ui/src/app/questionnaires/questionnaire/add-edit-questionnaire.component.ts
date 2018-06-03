import {QuestionnaireService} from "../../shared/services/questionnaire.service";
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Questionnaire} from "../../shared/services/questionnaire.model";
import {MatTableDataSource} from "@angular/material";
import {CandidateQuestion} from "../../shared/services/candidateQuestions.modal";

@Component({
  selector: 'add-edit-questionnaire',
  templateUrl: './add-edit-questionnaire.component.html',
  styleUrls: ['./add-edit-questionnaire.component.scss']
})
export class AddEditQuestionnaireComponent implements OnInit {
  isNew: boolean;
  questionnaireId: string;
  modal: Questionnaire;
  dataSource: MatTableDataSource<CandidateQuestion>;
  displayedColumns: string[] = ['candidateAnswer', 'question', 'question-answer', 'question-difficulty', 'question-category'];


  constructor(private questionnaireService: QuestionnaireService, private activeRoute: ActivatedRoute) {
      this.questionnaireId = this.activeRoute.snapshot.params["id"];
  }

  ngOnInit() {
      this.modal = this.questionnaireId != null ?
          this.questionnaireService.findById(this.questionnaireId) :
          Questionnaire.newOne();

      this.dataSource = this.modal.candidateQuestions != null ?
         new MatTableDataSource<CandidateQuestion>(this.modal.candidateQuestions):
         new MatTableDataSource<CandidateQuestion>();
  }
}


