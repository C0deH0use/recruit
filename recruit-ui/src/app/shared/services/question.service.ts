import { Injectable } from '@angular/core';
import { Question }   from "../questions/question.modal";
import { Observable } from "rxjs/Observable";

@Injectable ()
export class QuestionService {
  private questions: Question[];

  constructor () {
    this.questions = [
      new Question ("1","Question Junior JAVA 1", "Correct Answer", "Junior", "JAVA"),
      new Question ("2", "Question Junior JAVA 2", "Correct Answer", "Junior", "JAVA"),
      new Question ("3", "Question Junior JAVA 3", "Correct Answer", "Junior", "JAVA"),

      new Question ("4", "Question Mid JAVA 1", "Correct Answer", "Mid", "JAVA"),
      new Question ("6", "Question Mid JAVA 2", "Correct Answer", "Mid", "JAVA"),

      new Question ("5", "Question Junior Security 1", "Correct Answer", "Mid", "SECURITY"),
      new Question ("7", "Question Mid Security 3", "Correct Answer", "Junior", "SECURITY"),
      new Question ("8", "Question Junior Security 2", "Correct Answer", "Mid", "SECURITY"),

      new Question ("10", "Question Mid Web 2", "Correct Answer", "Mid", "Web"),
      new Question ("9", "Question Mid Web 1", "Correct Answer", "Mid", "Web")
    ];
  }

  public getQuestions (): Observable<Question[]> {
    return new Observable<Question[]> (observer => observer.next (this.questions.slice()));
  }

}
