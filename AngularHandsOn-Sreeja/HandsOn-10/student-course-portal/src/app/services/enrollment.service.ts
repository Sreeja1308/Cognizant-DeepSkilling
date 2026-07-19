import { Injectable } from '@angular/core';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService {
  private enrolledCoursesIds: number[] = [];
  private currentCourseId: number | null = null;

  constructor(private courseService: CourseService) {}

  enroll(courseId: number): void {
    if (!this.enrolledCoursesIds.includes(courseId)) {
      this.enrolledCoursesIds.push(courseId);
      this.currentCourseId = courseId;
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCoursesIds = this.enrolledCoursesIds.filter(id => id !== courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCoursesIds.includes(courseId);
  }

  getEnrolledCourses(): Course[] {
    const courses: Course[] = [];
    this.courseService.getCourses().subscribe(allCourses => {
      this.enrolledCoursesIds.forEach(id => {
        const found = allCourses.find(c => c.id === id);
        if (found) {
          courses.push(found);
        }
      });
    });
    return courses;
  }

  getEnrolledCourseIds(): number[] {
    return [...this.enrolledCoursesIds];
  }

  getCurrentCourseId(): number | null {
    return this.currentCourseId;
  }
}