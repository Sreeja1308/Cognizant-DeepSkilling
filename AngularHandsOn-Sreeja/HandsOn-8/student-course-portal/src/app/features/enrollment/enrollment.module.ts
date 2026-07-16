import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { EnrollmentFormComponent } from '../../pages/enrollment-form/enrollment-form.component';
import { ReactiveEnrollmentFormComponent } from '../../pages/reactive-enrollment-form/reactive-enrollment-form.component';

@NgModule({
  declarations: [
    EnrollmentFormComponent,
    ReactiveEnrollmentFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild([
      { path: '', component: EnrollmentFormComponent },
      { path: 'reactive', component: ReactiveEnrollmentFormComponent, canDeactivate: [unsavedChangesGuard] }
    ])
  ]
})
export class EnrollmentModule { }