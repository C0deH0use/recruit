import { Category }  from "./category.modal";
import { Dificulty } from "./dificulty.modal";

export class Question {
  constructor(
              public id: string,
              public question:string,
              public answer:string,
              public difficulty: Dificulty,
              public category: Category){}
  get questionText():string{
    return this.question;
  }

}
