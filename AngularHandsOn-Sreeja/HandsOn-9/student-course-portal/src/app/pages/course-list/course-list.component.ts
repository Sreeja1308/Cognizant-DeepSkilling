import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { Course } from '../../models/course.model';
import * as CourseActions from '../../store/course/course.actions';
import * as EnrollmentActions from '../../store/enrollment/enrollment.actions';
import { selectAllCourses, selectCoursesLoading, selectCoursesError } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, CourseCardComponent, RouterModule, FormsModule],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent implements OnInit, OnDestroy {
  courses$: Observable<Course[]>;
  loading$: Observable<boolean>;
  error$: Observable<string | null>;
  enrolledIds$: Observable<number[]>;
  selectedCourseId: number | null = null;
  searchTerm: string = '';
  private subscriptions: Subscription[] = [];

  constructor(
    private store: Store,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.courses$ = this.store.select(selectAllCourses);
    this.loading$ = this.store.select(selectCoursesLoading);
    this.error$ = this.store.select(selectCoursesError);
    this.enrolledIds$ = this.store.select(selectEnrolledIds);
  }

  ngOnInit(): void {
    // Read query parameter from URL
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params['search'] || '';
    });

    // Dispatch actions to load data
    this.store.dispatch(CourseActions.loadCourses());
    this.store.dispatch(EnrollmentActions.loadEnrollments());
  }

  onEnroll(courseId: number): void {
    this.store.dispatch(EnrollmentActions.enrollInCourse({ courseId }));
    this.selectedCourseId = courseId;
  }

  onUnenroll(courseId: number): void {
    this.store.dispatch(EnrollmentActions.unenrollFromCourse({ courseId }));
    this.selectedCourseId = null;
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onCourseClick(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }

  onSearch(): void {
    this.router.navigate(['/courses'], {
      queryParams: { search: this.searchTerm || null }
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}