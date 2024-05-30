import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SpecificationImportComponent} from './specification-import/specification-import.component';
import {SpecificationService} from './domain/specification.service';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material.module';
import {AuthService} from '../authentication/auth.service';
import {Specification} from './domain/specification';
import {NamespaceListComponent} from '../namespace-management/namespace-list/namespace-list.component';
import {SpecificationListComponent} from './specification-list/specification-list.component';
import {ScoreCommonModule} from '../common/score-common.module';

const routes: Routes = [
  {
    path: 'specification',
    children: [
      {
        path: 'new',
        component: SpecificationImportComponent,
        canActivate: [AuthService],
      },
      {
        path: 'list',
        component: SpecificationListComponent,
        canActivate: [AuthService],
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    CommonModule,
    ScoreCommonModule
  ],
  declarations: [
    SpecificationImportComponent,
    SpecificationListComponent
  ],
  providers: [
    SpecificationService
  ]
})
export class SpecificationManagementModule {
}
