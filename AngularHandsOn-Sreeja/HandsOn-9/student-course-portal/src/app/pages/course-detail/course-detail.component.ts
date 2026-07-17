import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { Course } from '../../models/course.model';
import * as CourseActions from '../../store/course/course.actions';
import * as EnrollmentActions from '../../store/enrollment/enrollment.actions';
import { selectAllCourses, selectCoursesLoading } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './course-detail.component.html',
  styleUrl: './course-detail.component.css'
})
export class CourseDetailComponent implements OnInit, OnDestroy {
  course: Course | undefined;
  courseId: string | null = null;
  isLoading$: Observable<boolean>;
  courses$: Observable<Course[]>;
  enrolledIds$: Observable<number[]>;
  isEnrolled = false;
  private subscription: Subscription | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private store: Store
  ) {
    this.courses$ = this.store.select(selectAllCourses);
    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.enrolledIds$ = this.store.select(selectEnrolledIds);
  }

  ngOnInit(): void {
    // Read the :id parameter from the route
    this.courseId = this.route.snapshot.paramMap.get('id');
    
    // Subscribe to courses and enrolledIds to find the course
    this.subscription = this.courses$.subscribe(courses => {
      if (this.courseId) {
        const id = parseInt(this.courseId, 10);
        this.course = courses.find(c => c.id === id);
        if (!this.course) {
          this.router.navigate(['/not-found']);
        }
      }
    });

    // Subscribe to enrolledIds to check enrollment status
    this.enrolledIds$.subscribe(ids => {
      if (this.course) {
        this.isEnrolled = ids.includes(this.course.id);
      }
    });
  }

  toggleEnrollment(): void {
    if (this.course) {
      if (this.isEnrolled) {
        this.store.dispatch(EnrollmentActions.unenrollFromCourse({ courseId: this.course.id }));
      } else {
        this.store.dispatch(EnrollmentActions.enrollInCourse({ courseId: this.course.id }));
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/courses']);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}