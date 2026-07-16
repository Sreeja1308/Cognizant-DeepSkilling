import { inject } from '@angular/core';
import { CanDeactivateFn } from '@angular/router';
import { ReactiveEnrollmentFormComponent } from '../pages/reactive-enrollment-form/reactive-enrollment-form.component';

export const unsavedChangesGuard: CanDeactivateFn<ReactiveEnrollmentFormComponent> = (component) => {
  if (component.enrollForm && component.enrollForm.dirty) {
    return window.confirm('You have unsaved changes. Are you sure you want to leave?');
  }
  return true;
};