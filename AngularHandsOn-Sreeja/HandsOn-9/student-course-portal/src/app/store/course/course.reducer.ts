import { createReducer, on } from '@ngrx/store';
import { Course } from '../../models/course.model';
import * as CourseActions from './course.actions';

export interface CourseState {
  courses: Course[];
  loading: boolean;
  error: string | null;
}

export const initialState: CourseState = {
  courses: [],
  loading: false,
  error: null
};

export const courseReducer = createReducer(
  initialState,

  // Load Courses
  on(CourseActions.loadCourses, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(CourseActions.loadCoursesSuccess, (state, { courses }) => ({
    ...state,
    courses,
    loading: false,
    error: null
  })),
  on(CourseActions.loadCoursesFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),

  // Add Course
  on(CourseActions.addCourse, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(CourseActions.addCourseSuccess, (state, { course }) => ({
    ...state,
    courses: [...state.courses, course],
    loading: false,
    error: null
  })),
  on(CourseActions.addCourseFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),

  // Update Course
  on(CourseActions.updateCourse, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(CourseActions.updateCourseSuccess, (state, { course }) => ({
    ...state,
    courses: state.courses.map(c => c.id === course.id ? course : c),
    loading: false,
    error: null
  })),
  on(CourseActions.updateCourseFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),

  // Delete Course
  on(CourseActions.deleteCourse, (state) => ({
    ...state,
    loading: true,
    error: null
  })),
  on(CourseActions.deleteCourseSuccess, (state, { id }) => ({
    ...state,
    courses: state.courses.filter(c => c.id !== id),
    loading: false,
    error: null
  })),
  on(CourseActions.deleteCourseFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  }))
);