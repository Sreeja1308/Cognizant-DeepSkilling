import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map } from 'rxjs/operators';
import { EnrollmentService } from '../../services/enrollment.service';
import * as EnrollmentActions from './enrollment.actions';

@Injectable()
export class EnrollmentEffects {
  constructor(
    private actions$: Actions,
    private enrollmentService: EnrollmentService
  ) {
    console.log('EnrollmentEffects initialized');
  }

  enroll$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(EnrollmentActions.enrollInCourse),
      map(({ courseId }) => {
        console.log('Enroll effect triggered for course:', courseId);
        this.enrollmentService.enroll(courseId);
        return EnrollmentActions.setEnrolledCourses({
          courseIds: this.enrollmentService.getEnrolledCourseIds()
        });
      })
    );
  });

  unenroll$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(EnrollmentActions.unenrollFromCourse),
      map(({ courseId }) => {
        console.log('Unenroll effect triggered for course:', courseId);
        this.enrollmentService.unenroll(courseId);
        return EnrollmentActions.setEnrolledCourses({
          courseIds: this.enrollmentService.getEnrolledCourseIds()
        });
      })
    );
  });
}