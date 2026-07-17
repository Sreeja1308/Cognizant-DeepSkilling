import { createAction, props } from '@ngrx/store';
import { Course } from '../../models/course.model';

// Load Courses
export const loadCourses = createAction('[Course] Load Courses');
export const loadCoursesSuccess = createAction(
  '[Course] Load Courses Success',
  props<{ courses: Course[] }>()
);
export const loadCoursesFailure = createAction(
  '[Course] Load Courses Failure',
  props<{ error: string }>()
);

// Add Course
export const addCourse = createAction(
  '[Course] Add Course',
  props<{ course: Omit<Course, 'id'> }>()
);
export const addCourseSuccess = createAction(
  '[Course] Add Course Success',
  props<{ course: Course }>()
);
export const addCourseFailure = createAction(
  '[Course] Add Course Failure',
  props<{ error: string }>()
);

// Update Course
export const updateCourse = createAction(
  '[Course] Update Course',
  props<{ id: number; course: Partial<Course> }>()
);
export const updateCourseSuccess = createAction(
  '[Course] Update Course Success',
  props<{ course: Course }>()
);
export const updateCourseFailure = createAction(
  '[Course] Update Course Failure',
  props<{ error: string }>()
);

// Delete Course
export const deleteCourse = createAction(
  '[Course] Delete Course',
  props<{ id: number }>()
);
export const deleteCourseSuccess = createAction(
  '[Course] Delete Course Success',
  props<{ id: number }>()
);
export const deleteCourseFailure = createAction(
  '[Course] Delete Course Failure',
  props<{ error: string }>()
);