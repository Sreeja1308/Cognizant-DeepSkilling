import { createAction, props } from '@ngrx/store';

// Load Enrollments
export const loadEnrollments = createAction('[Enrollment] Load Enrollments');
export const loadEnrollmentsSuccess = createAction(
  '[Enrollment] Load Enrollments Success',
  props<{ enrolledCourseIds: number[] }>()
);
export const loadEnrollmentsFailure = createAction(
  '[Enrollment] Load Enrollments Failure',
  props<{ error: string }>()
);

// Enroll in Course
export const enrollInCourse = createAction(
  '[Enrollment] Enroll In Course',
  props<{ courseId: number }>()
);
export const enrollInCourseSuccess = createAction(
  '[Enrollment] Enroll In Course Success',
  props<{ courseId: number }>()
);
export const enrollInCourseFailure = createAction(
  '[Enrollment] Enroll In Course Failure',
  props<{ error: string }>()
);

// Unenroll from Course
export const unenrollFromCourse = createAction(
  '[Enrollment] Unenroll From Course',
  props<{ courseId: number }>()
);
export const unenrollFromCourseSuccess = createAction(
  '[Enrollment] Unenroll From Course Success',
  props<{ courseId: number }>()
);
export const unenrollFromCourseFailure = createAction(
  '[Enrollment] Unenroll From Course Failure',
  props<{ error: string }>()
);

// Reset Enrollments
export const resetEnrollments = createAction('[Enrollment] Reset Enrollments');