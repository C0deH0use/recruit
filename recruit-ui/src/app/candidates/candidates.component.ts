import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource }   from "@angular/material";
import { CandidatesService }                           from "../shared/services/candidates.service";
import { Router }                                      from "@angular/router";
import { Candidate }                                   from "../shared/candidates/candidate.modal";

@Component ({
  selector: 'app-candidates',
  templateUrl: './candidates.component.html',
  styleUrls: [ './candidates.component.scss' ]
})
export class CandidatesComponent implements OnInit, AfterViewInit {

  @ViewChild (MatSort) sort: MatSort;
  @ViewChild (MatPaginator) paginator: MatPaginator;

  pageSizeOptions: number[] = [ 5, 10, 25, 100 ];
  displayedColumns: string[] = [ 'action', 'id', 'name', 'surname', 'email', 'recruitedDate' ];

  dataSource: MatTableDataSource<Candidate>;

  constructor (private candidatesService: CandidatesService, private router: Router) {
  }

  ngOnInit () {
    this.candidatesService.getCandidates ()
      .subscribe (candidates =>
        this.dataSource = new MatTableDataSource<Candidate> (candidates));
  }

  ngAfterViewInit () {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  onNewCandidate() {

  }

  onNavigateToDetails(id: string){

  }

}
