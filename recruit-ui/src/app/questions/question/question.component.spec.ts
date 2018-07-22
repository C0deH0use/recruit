import { async, ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';

import { QuestionComponent }            from './question.component';
import { routes }                       from "../../shared/routes/full-layout.routes";
import { RouterTestingModule }          from "@angular/router/testing";
import { ActivatedRoute, Data, Router } from "@angular/router";
import { Observable }                   from "rxjs/Observable";
import { QuestionService }              from "../../shared/services/question.service";
import { QuestionnaireService }         from "../../shared/services/questionnaire.service";

describe('QuestionComponent', () => {
  let component: QuestionComponent;
  let fixture: ComponentFixture<QuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionComponent ],
      imports: [ RouterTestingModule.withRoutes(routes)  ],
      providers : [
        QuestionService, QuestionnaireService,
        {
          provide :  ActivatedRoute ,
          useValue: {
            data : new Observable<Data>(observable =>  observable.next({"isNew" : false ,  "questionType": "Question" }))
          }
        }
      ]
    });

  }));

  it('should create', fakeAsync(() => {
    fixture = TestBed.createComponent(QuestionComponent);
    TestBed.get(Router).navigate(['/questions/by-id/1']);
    component = fixture.componentInstance;
    tick();

    expect(component.isNew).toBeFalsy();
    expect(component.withAnswer).toBeFalsy();
    // expect(component.questionId).toBe(1);
  }));
});
