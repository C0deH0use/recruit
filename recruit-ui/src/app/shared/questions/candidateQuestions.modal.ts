import {Question}    from "./question.modal";
import { Dificulty } from "./dificulty.modal";
import { Category }  from "./category.modal";

export class CandidateQuestion {
  constructor(              public id: string,
                            public candidateAnswer: string, public question: Question){}

  get questionText():string{
    return this.question.question;
  }
  set questionText(newValue: string) {
    this.question.question = newValue;
  }
  get answer(): string {
    return this.question.answer;
  }
  set answer(newValue: string) {
    this.question.answer = newValue;
  }
  get difficulty(): Dificulty {
    return this.question.difficulty;
  }
  set difficulty(newValue: Dificulty) {
    this.question.difficulty = newValue;
  }
  get category(): Category {
    return this.question.category;
  }
  set category(newValue: Category) {
    this.question.category = newValue;
  }
}


