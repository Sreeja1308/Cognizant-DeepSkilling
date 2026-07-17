import { createReducer, on } from '@ngrx/store';
import * as EnrollmentActions from './enrollment.actions';

export interface EnrollmentState {
  enrolledCourseIds: number[];
  loading: boolean;
  error: string | null;
}

export const initialState: EnrollmentState = {
  enrolledCourseIds: [],
  loading: false,
  error: null
};

export const enrollmentReducer = createReducer(
  initialState,

  on(EnrollmentActions.loadEnrollments, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(EnrollmentActions.loadEnrollmentsSuccess, (state, { enrolledCourseIds }) => ({
    ...state,
    enrolledCourseIds,
    loading: false,
    error: null
  })),
  on(EnrollmentActions.loadEnrollmentsFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),

  on(EnrollmentActions.enrollInCourse, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(EnrollmentActions.enrollInCourseSuccess, (state, { courseId }) => ({
    ...state,
    enrolledCourseIds: [...state.enrolledCourseIds, courseId],
    loading: false,
    error: null
  })),
  on(EnrollmentActions.enrollInCourseFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),

  on(EnrollmentActions.unenrollFromCourse, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(EnrollmentActions.unenrollFromCourseSuccess, (state, { courseId }) => ({
    ...state,
    enrolledCourseIds: state.enrolledCourseIds.filter(id => id !== courseId),
    loading: false,
    error: null
  })),
  on(EnrollmentActions.unenrollFromCourseFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),

  on(EnrollmentActions.resetEnrollments, (state) => ({
    ...state,
    enrolledCourseIds: [],
    loading: false,
    error: null
  }))
);