import { createFeatureSelector, createSelector } from '@ngrx/store';
import { EnrollmentState } from './enrollment.reducer';
import { selectAllCourses } from '../course/course.selectors';

export const selectEnrollmentState = createFeatureSelector<EnrollmentState>('enrollment');

export const selectEnrolledIds = createSelector(
  selectEnrollmentState,
  (state: EnrollmentState) => state.enrolledCourseIds
);

export const selectEnrollmentsLoading = createSelector(
  selectEnrollmentState,
  (state: EnrollmentState) => state.loading
);

export const selectEnrollmentsError = createSelector(
  selectEnrollmentState,
  (state: EnrollmentState) => state.error
);

// Cross-slice selector: combines course and enrollment state
export const selectEnrolledCourses = createSelector(
  selectAllCourses,
  selectEnrolledIds,
  (courses, enrolledIds) => courses.filter(course => enrolledIds.includes(course.id))
);

export const selectIsEnrolled = (courseId: number) => createSelector(
  selectEnrolledIds,
  (enrolledIds) => enrolledIds.includes(courseId)
);