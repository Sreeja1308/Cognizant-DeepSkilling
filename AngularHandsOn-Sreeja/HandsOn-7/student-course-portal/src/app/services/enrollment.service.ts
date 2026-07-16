import { Injectable } from '@angular/core';
import { Course } from '../models/course.model';
import { CourseService } from './course.service';

@Injectable({
  providedIn: 'root' // Singleton service - one instance shared across the entire application
})
export class EnrollmentService {
  private enrolledCourseIds: number[] = [];

  constructor(private courseService: CourseService) {} // Service-to-service injection

  enroll(courseId: number): void {
    if (!this.isEnrolled(courseId)) {
      this.enrolledCourseIds.push(courseId);
      console.log(`Enrolled in course ID: ${courseId}`);
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCourseIds = this.enrolledCourseIds.filter(id => id !== courseId);
    console.log(`Unenrolled from course ID: ${courseId}`);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourses(): Course[] {
    const enrolledCourses: Course[] = [];
    this.enrolledCourseIds.forEach(id => {
      const course = this.courseService.getCourseById(id);
      if (course) {
        enrolledCourses.push(course);
      }
    });
    return enrolledCourses;
  }

  getEnrolledCourseIds(): number[] {
    return [...this.enrolledCourseIds];
  }

  toggleEnrollment(courseId: number): void {
    if (this.isEnrolled(courseId)) {
      this.unenroll(courseId);
    } else {
      this.enroll(courseId);
    }
  }
}