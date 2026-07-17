import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError, switchMap } from 'rxjs/operators';
import { Course } from '../models/course.model';
import { CourseService } from './course.service';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {
  private apiUrl = 'http://localhost:3000/enrollments';
  private coursesUrl = 'http://localhost:3000/courses';
  private enrolledCourseIds: number[] = [];

  constructor(
    private http: HttpClient,
    private courseService: CourseService
  ) {}

  enroll(courseId: number): Observable<any> {
    const enrollment = { studentId: 1, courseId: courseId };
    return this.http.post(this.apiUrl, enrollment).pipe(
      map(() => {
        if (!this.isEnrolled(courseId)) {
          this.enrolledCourseIds.push(courseId);
        }
        console.log(`Enrolled in course ID: ${courseId}`);
        return { success: true };
      }),
      catchError((error) => {
        console.error('Error enrolling:', error);
        return throwError(() => new Error('Failed to enroll in course.'));
      })
    );
  }

  unenroll(courseId: number): Observable<any> {
    // Find enrollment id
    return this.http.get<any[]>(this.apiUrl).pipe(
      switchMap((enrollments) => {
        const enrollment = enrollments.find(e => e.courseId === courseId && e.studentId === 1);
        if (enrollment) {
          return this.http.delete(`${this.apiUrl}/${enrollment.id}`).pipe(
            map(() => {
              this.enrolledCourseIds = this.enrolledCourseIds.filter(id => id !== courseId);
              console.log(`Unenrolled from course ID: ${courseId}`);
              return { success: true };
            })
          );
        }
        return of({ success: false, message: 'Enrollment not found' });
      }),
      catchError((error) => {
        console.error('Error unenrolling:', error);
        return throwError(() => new Error('Failed to unenroll from course.'));
      })
    );
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourses(): Observable<Course[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      switchMap((enrollments) => {
        const courseIds = enrollments
          .filter(e => e.studentId === 1)
          .map(e => e.courseId);
        return this.http.get<Course[]>(this.coursesUrl).pipe(
          map(courses => courses.filter(c => courseIds.includes(c.id)))
        );
      }),
      catchError((error) => {
        console.error('Error fetching enrolled courses:', error);
        return throwError(() => new Error('Failed to load enrolled courses.'));
      })
    );
  }

  // Load enrolled course IDs from server
  loadEnrolledCourseIds(): Observable<number[]> {
    return this.http.get<any[]>(this.apiUrl).pipe(
      map((enrollments) => {
        const ids = enrollments
          .filter(e => e.studentId === 1)
          .map(e => e.courseId);
        this.enrolledCourseIds = ids;
        return ids;
      }),
      catchError((error) => {
        console.error('Error loading enrollments:', error);
        return throwError(() => new Error('Failed to load enrollments.'));
      })
    );
  }
}