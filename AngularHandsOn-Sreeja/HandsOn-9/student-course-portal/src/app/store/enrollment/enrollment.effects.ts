import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';
import { EnrollmentService } from '../../services/enrollment.service';
import * as EnrollmentActions from './enrollment.actions';

@Injectable()
export class EnrollmentEffects {
  constructor(
    private actions$: Actions,
    private enrollmentService: EnrollmentService
  ) {}

  loadEnrollments$ = createEffect(() =>
    this.actions$.pipe(
      ofType(EnrollmentActions.loadEnrollments),
      switchMap(() =>
        this.enrollmentService.loadEnrolledCourseIds().pipe(
          map(enrolledCourseIds =>
            EnrollmentActions.loadEnrollmentsSuccess({ enrolledCourseIds })
          ),
          catchError(error =>
            of(EnrollmentActions.loadEnrollmentsFailure({ error: error.message }))
          )
        )
      )
    )
  );

  enrollInCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(EnrollmentActions.enrollInCourse),
      switchMap(({ courseId }) =>
        this.enrollmentService.enroll(courseId).pipe(
          map(() => EnrollmentActions.enrollInCourseSuccess({ courseId })),
          catchError(error =>
            of(EnrollmentActions.enrollInCourseFailure({ error: error.message }))
          )
        )
      )
    )
  );

  unenrollFromCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(EnrollmentActions.unenrollFromCourse),
      switchMap(({ courseId }) =>
        this.enrollmentService.unenroll(courseId).pipe(
          map(() => EnrollmentActions.unenrollFromCourseSuccess({ courseId })),
          catchError(error =>
            of(EnrollmentActions.unenrollFromCourseFailure({ error: error.message }))
          )
        )
      )
    )
  );
}