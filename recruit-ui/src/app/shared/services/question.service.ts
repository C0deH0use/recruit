import { Injectable } from '@angular/core';
import { Question }   from "../questions/question.modal";
import { Observable } from "rxjs/Observable";
import { Dificulty }  from "../questions/dificulty.modal";
import { Category }   from "../questions/category.modal";
import { UUID }       from "angular2-uuid";

@Injectable ()
export class QuestionService {
  private questions: Question[];

  constructor () {
    this.questions = [
      new Question ("1","Question Junior JAVA 1", "Correct Answer", Dificulty.JUNIOR, Category.JAVA_BASICS),
      new Question ("2", "Question Junior JAVA 2", "Correct Answer", Dificulty.JUNIOR, Category.JAVA_BASICS),
      new Question ("3", "Question Junior JAVA 3", "Correct Answer", Dificulty.JUNIOR, Category.JAVA_BASICS),

      new Question ("4", "Question Mid JAVA 1", "Correct Answer", Dificulty.MID, Category.JAVA_BASICS),
      new Question ("6", "Question Mid JAVA 2", "Correct Answer", Dificulty.MID, Category.JAVA_ADVANCED),

      new Question ("5", "Question Junior Security 1", "Correct Answer", Dificulty.MID, Category.SECURITY),
      new Question ("7", "Question Mid Security 3", "Correct Answer", Dificulty.JUNIOR, Category.SECURITY),
      new Question ("8", "Question Junior Security 2", "Correct Answer", Dificulty.MID, Category.SECURITY),

      new Question ("10", "Question Mid Web 2", "Correct Answer", Dificulty.MID, Category.WEB),
      new Question ("9", "Question Mid Web 1", "Correct Answer", Dificulty.MID, Category.WEB)
    ];
  }

  public save( question: Question) : Observable<Question[]> {
      if(question.id == null) {
        question.id = UUID.UUID();
      }
      this.questions.push(question);
      return this.getQuestions();
  }

  public getQuestions () : Observable<Question[]> {
    return new Observable<Question[]> (observer => observer.next (this.questions.slice()));
  }

  public findQuestionById (questionId : string) : Observable<Question> {
    let question : Question = this.questions.find(question => question.id === questionId);
    return new Observable<Question>(observer => observer.next(question));
  }

}
