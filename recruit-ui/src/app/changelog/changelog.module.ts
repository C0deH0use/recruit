import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { ChangeLogRoutingModule } from "./changelog-routing.module";
import { MatchHeightModule } from "../shared/directives/match-height.directive";

import { ChangeLogComponent } from "./changelog.component";



@NgModule({
    imports: [
        CommonModule,
        MatchHeightModule,
        ChangeLogRoutingModule
    ],
    exports: [],
    declarations: [ ChangeLogComponent ],
    providers: [],
})
export class ChangeLogModule { }
