import {EventEmitter, Injectable} from "@angular/core";
import {Questionnaire} from "./questionnaire.model";
import {Observable} from "rxjs/Observable";
import {Question} from "./question.model";
import {CandidateQuestion} from "./candidateQuestions.modal";

@Injectable()
export class QuestionnaireService {
  selected = new EventEmitter<Questionnaire>();

  private questionnaires: Questionnaire[];

  constructor() {
    this.questionnaires = [
      new Questionnaire("1a", "2018-05-05", "Marek", "", [
          new CandidateQuestion("Incorrect Answer", new Question("Question 1", "Correct Answer", "Junior", "JAVA")),
          new CandidateQuestion("Correct Answer ", new Question("Question 2", "Correct Answer", "Junior", "JAVA")),
          new CandidateQuestion("Correct Answer ", new Question("Question 3", "Correct Answer", "Mid", "JAVA"))
      ]),
      new Questionnaire("2a", "2018-05-05", "Alek", "", [
        new CandidateQuestion("Correct Answer", new Question("Question 1", "Correct Answer", "Junior", "JAVA")),
        new CandidateQuestion("Incorrect Answer ", new Question("Question 2", "Correct Answer", "Mid", "JAVA")),
        new CandidateQuestion("Correct Answer ", new Question("Question 3", "Correct Answer", "Mid", "JAVA"))
      ]),
      new Questionnaire("3a", "2018-05-05", "Bartek", "", [
        new CandidateQuestion("Correct Answer", new Question("Question 1", "Correct Answer", "Junior", "JAVA")),
        new CandidateQuestion("Incorrect Answer ", new Question("Question 2", "Correct Answer", "Mid", "JAVA")),
        new CandidateQuestion("Correct Answer ", new Question("Question 3", "Correct Answer", "Mid", "JAVA"))
      ]),
      new Questionnaire("4a", "2018-05-05", "Damian", "", null)
    ];
  }

  getQuestionnaires(): Observable<Questionnaire[]> {
    return new Observable<Questionnaire[]>(observer => observer.next(this.questionnaires));
  }

  findById(questionnaireId: string) : Questionnaire {
    return  this.questionnaires.filter((value: Questionnaire) => value.id === questionnaireId)[0];
  }
}
