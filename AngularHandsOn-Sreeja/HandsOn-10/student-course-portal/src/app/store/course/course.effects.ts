import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, exhaustMap, catchError } from 'rxjs/operators';
import { CourseService } from '../../services/course.service';
import * as CourseActions from './course.actions';

@Injectable()
export class CourseEffects {
  constructor(
    private actions$: Actions,
    private courseService: CourseService
  ) {
    console.log('CourseEffects initialized');
  }

  loadCourses$ = createEffect(() => {
    console.log('Creating loadCourses$ effect');
    return this.actions$.pipe(
      ofType(CourseActions.loadCourses),
      exhaustMap((action) => {
        console.log('loadCourses action received', action);
        return this.courseService.getCourses().pipe(
          map(courses => {
            console.log('Courses loaded successfully', courses);
            return CourseActions.loadCoursesSuccess({ courses });
          }),
          catchError(error => {
            console.error('Error loading courses', error);
            return of(CourseActions.loadCoursesFailure({ error: error.message }));
          })
        );
      })
    );
  });
}