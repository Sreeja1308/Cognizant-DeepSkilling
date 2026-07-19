import { AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { Observable, of, delay } from 'rxjs';

export function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const value = control.value;
  if (value && value.toString().toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

export function simulateEmailCheck(control: AbstractControl): Observable<ValidationErrors | null> {
  const email = control.value;
  return of(email && email.includes('test@') ? { emailTaken: true } : null).pipe(
    delay(800)
  );
}