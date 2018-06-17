import {EventEmitter, Injectable} from "@angular/core";
import {Questionnaire}            from "../questionnaries/questionnaire.modal";
import {Observable}               from "rxjs/Observable";
import {Question}                 from "../questions/question.modal";
import {CandidateQuestion}        from "../questions/candidateQuestions.modal";

@Injectable()
export class QuestionnaireService {
  private questionnaires: Questionnaire[];

  constructor() {
    this.questionnaires = [
      new Questionnaire("1a", "2018-05-05", "Marek", "", [
          new CandidateQuestion("1", "Incorrect Answer", new Question("1", "Question Junior JAVA 1", "Correct Answer", "Junior", "JAVA")),
          new CandidateQuestion("2", "Correct Answer ", new Question("2", "Question Junior JAVA 2", "Correct Answer", "Junior", "JAVA")),
          new CandidateQuestion("3", "Correct Answer ", new Question("4", "Question Mid JAVA 1", "Correct Answer", "Mid", "JAVA"))
      ]),
      new Questionnaire("2a", "2018-05-05", "Alek", "", [
        new CandidateQuestion("4", "Correct Answer", new Question("1", "Question Junior JAVA 1", "Correct Answer", "Junior", "JAVA")),
        new CandidateQuestion("5", "Incorrect Answer ", new Question("4", "Question Mid JAVA 1", "Correct Answer", "Mid", "JAVA")),
        new CandidateQuestion("6", "Correct Answer ", new Question("6", "Question Mid JAVA 2", "Correct Answer", "Mid", "JAVA"))
      ]),
      new Questionnaire("3a", "2018-05-05", "Bartek", "", [
        new CandidateQuestion("7", "Correct Answer", new Question("1", "Question Junior JAVA 1", "Correct Answer", "Junior", "JAVA")),
        new CandidateQuestion("8", "Incorrect Answer ", new Question("4", "Question Mid JAVA 1","Correct Answer", "Mid", "JAVA")),
        new CandidateQuestion("9", "Correct Answer ", new Question("5", "Question Junior Security 1", "Correct Answer", "Mid", "SECURITY"))
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
