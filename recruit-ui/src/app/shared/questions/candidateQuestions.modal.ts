import {Question} from "./question.modal";

export class CandidateQuestion {
  constructor(              private id: string,
                            public candidateAnswer: string, public question: Question){}

  get questionText():string{
    return this.question.question;
  }
  get answer(): string {
    return this.question.answer;
  }
  get difficulty(): string {
    return this.question.difficulty;
  }
  get category(): string {
    return this.question.category;
  }
  getId(): string {
    return this.id;
  }
}


