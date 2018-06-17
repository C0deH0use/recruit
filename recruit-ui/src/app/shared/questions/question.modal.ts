export class Question {
  constructor(
              private id: string,
              public question:string,
              public answer:string,
              public difficulty:string,
              public category:string){}
  get questionText():string{
    return this.question;
  }
  getId():string{
    return this.id;
  }
}
