import {CandidateQuestion} from "./candidateQuestions.modal";

export class Questionnaire {
  public static newOne(): Questionnaire {
    return new Questionnaire(null, null, null, null, []);
  }
  constructor(public id:string, public createdDate: string, public user:string, public additionalNotes:string, public candidateQuestions: CandidateQuestion[]){}
}
