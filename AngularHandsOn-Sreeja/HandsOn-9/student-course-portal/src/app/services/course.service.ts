import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap, retry } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) {}

  // Get all courses with RxJS operators
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl).pipe(
      // tap is for side effects (logging) - preferred over side effects inside map
      tap(courses => console.log('Courses loaded:', courses.length)),
      // map to transform the response
      map(courses => courses.filter(c => c.credits > 0)),
      // retry failed requests up to 2 times
      retry(2),
      // error handling
      catchError((error) => {
        console.error('Error fetching courses:', error);
        return throwError(() => new Error('Failed to load courses. Please try again.'));
      })
    );
  }

  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`).pipe(
      catchError((error) => {
        console.error(`Error fetching course ${id}:`, error);
        return throwError(() => new Error('Failed to load course details.'));
      })
    );
  }

  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course).pipe(
      tap(newCourse => console.log('Course created:', newCourse)),
      catchError((error) => {
        console.error('Error creating course:', error);
        return throwError(() => new Error('Failed to create course.'));
      })
    );
  }

  updateCourse(id: number, course: Partial<Course>): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${id}`, course).pipe(
      tap(updated => console.log('Course updated:', updated)),
      catchError((error) => {
        console.error(`Error updating course ${id}:`, error);
        return throwError(() => new Error('Failed to update course.'));
      })
    );
  }

  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => console.log('Course deleted:', id)),
      catchError((error) => {
        console.error(`Error deleting course ${id}:`, error);
        return throwError(() => new Error('Failed to delete course.'));
      })
    );
  }
}