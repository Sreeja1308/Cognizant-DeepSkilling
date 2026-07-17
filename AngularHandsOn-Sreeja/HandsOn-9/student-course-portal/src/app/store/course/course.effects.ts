import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';
import { CourseService } from '../../services/course.service';
import * as CourseActions from './course.actions';

@Injectable()
export class CourseEffects {
  constructor(
    private actions$: Actions,
    private courseService: CourseService
  ) {}

  loadCourses$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CourseActions.loadCourses),
      switchMap(() =>
        this.courseService.getCourses().pipe(
          map(courses => CourseActions.loadCoursesSuccess({ courses })),
          catchError(error =>
            of(CourseActions.loadCoursesFailure({ error: error.message }))
          )
        )
      )
    )
  );

  addCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CourseActions.addCourse),
      switchMap(({ course }) =>
        this.courseService.createCourse(course).pipe(
          map(newCourse => CourseActions.addCourseSuccess({ course: newCourse })),
          catchError(error =>
            of(CourseActions.addCourseFailure({ error: error.message }))
          )
        )
      )
    )
  );

  updateCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CourseActions.updateCourse),
      switchMap(({ id, course }) =>
        this.courseService.updateCourse(id, course).pipe(
          map(updatedCourse => CourseActions.updateCourseSuccess({ course: updatedCourse })),
          catchError(error =>
            of(CourseActions.updateCourseFailure({ error: error.message }))
          )
        )
      )
    )
  );

  deleteCourse$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CourseActions.deleteCourse),
      switchMap(({ id }) =>
        this.courseService.deleteCourse(id).pipe(
          map(() => CourseActions.deleteCourseSuccess({ id })),
          catchError(error =>
            of(CourseActions.deleteCourseFailure({ error: error.message }))
          )
        )
      )
    )
  );
}