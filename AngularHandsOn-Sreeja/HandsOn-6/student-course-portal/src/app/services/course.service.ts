import { Injectable } from '@angular/core';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root' // Singleton service - one instance shared across the entire application
})
export class CourseService {
  private courses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'Algorithms', code: 'CS102', credits: 3, gradeStatus: 'pending' },
    { id: 3, name: 'Database Systems', code: 'CS201', credits: 4, gradeStatus: 'failed' },
    { id: 4, name: 'Web Development', code: 'CS301', credits: 3, gradeStatus: 'pending' },
    { id: 5, name: 'Machine Learning', code: 'CS401', credits: 3, gradeStatus: 'passed' }
  ];

  getCourses(): Course[] {
    return this.courses;
  }

  getCourseById(id: number): Course | undefined {
    return this.courses.find(course => course.id === id);
  }

  addCourse(course: Omit<Course, 'id'>): void {
    const newId = this.courses.length > 0 ? Math.max(...this.courses.map(c => c.id)) + 1 : 1;
    this.courses.push({ ...course, id: newId });
  }

  updateCourse(id: number, updatedCourse: Partial<Course>): void {
    const index = this.courses.findIndex(course => course.id === id);
    if (index !== -1) {
      this.courses[index] = { ...this.courses[index], ...updatedCourse };
    }
  }

  deleteCourse(id: number): void {
    this.courses = this.courses.filter(course => course.id !== id);
  }
}