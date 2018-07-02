import {Question}    from "./question.modal";
import { Dificulty } from "./dificulty.modal";
import { Category }  from "./category.modal";

export class CandidateQuestion {
  constructor(              private id: string,
                            public candidateAnswer: string, public question: Question){}

  get questionText():string{
    return this.question.question;
  }
  get answer(): string {
    return this.question.answer;
  }
  get difficulty(): Dificulty {
    return this.question.difficulty;
  }
  get category(): Category {
    return this.question.category;
  }
  getId(): string {
    return this.id;
  }
}


