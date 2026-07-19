import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { selectAllCourses, selectCoursesLoading, selectCoursesError } from '../../store/course/course.selectors';
import { loadCourses } from '../../store/course/course.actions';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, CourseCardComponent],
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses$: Observable<Course[]>;
  isLoading$: Observable<boolean>;
  error$: Observable<string | null>;
  selectedCourseId: number | null = null;
  searchTerm = '';
  courses: Course[] = [];

  constructor(
    private store: Store,
    private router: Router,
    private enrollmentService: EnrollmentService,
    private cdr: ChangeDetectorRef
  ) {
    this.courses$ = this.store.select(selectAllCourses);
    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.error$ = this.store.select(selectCoursesError);
  }

  ngOnInit(): void {
    this.store.dispatch(loadCourses());
    this.courses$.subscribe(courses => {
      this.courses = courses;
      this.cdr.detectChanges();
    });
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onEnroll(courseId: number): void {
    this.selectedCourseId = courseId;
    this.enrollmentService.enroll(courseId);
    console.log('Enrolling in course:', courseId);
    this.cdr.detectChanges();
  }

  onCourseClick(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }
}