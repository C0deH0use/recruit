import { Injectable }     from '@angular/core';
import { Candidate }      from "../candidates/candidate.modal";
import { Observable, of } from "rxjs";

@Injectable ({
  providedIn: 'root'
})
export class CandidatesService {

  private candidates: Candidate[];

  constructor () {
    this.candidates = [
      new Candidate ("1", "Agnieszka", "Konopka", "aga@gmail.com", null),
      new Candidate ("2", "Marek", "Konopka", "marel@gmail.com", null),
      new Candidate ("3", "Marek", "Malik", "marekm@gmail.com", "2016-05-20"),
      new Candidate ("4", "Bartel", "Malik", "bmalik@gmail.com", "2017-05-20"),
      new Candidate ("5", "John", "Doe", "john@doe.com", "2018-05-20"),
      new Candidate ("6", "Lebron", "James", "lbj@nba.com", "2001-06-20"),
      new Candidate ("7", "Kevin", "Durant", "kd@nba.com", "2007-06-20")
    ];
  }

  getCandidates (): Observable<Candidate[]> {
    return of (this.candidates.slice ());
  }
}
