import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SpecificationImportComponent} from './specification-import/specification-import.component';
import {SpecificationService} from './domain/specification.service';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../material.module';
import {AuthService} from '../authentication/auth.service';
import {Specification} from './domain/specification';

const routes: Routes = [
  {
    path: 'new_specification',
    children: [
      {
        path: '',
        component: SpecificationImportComponent,
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
    CommonModule
  ],
  declarations: [
    SpecificationImportComponent
  ],
  providers: [
    SpecificationService
  ]
})
export class SpecificationManagementModule {
}
