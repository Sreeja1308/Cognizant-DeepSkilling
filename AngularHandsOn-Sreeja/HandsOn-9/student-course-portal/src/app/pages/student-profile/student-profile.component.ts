import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable, Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { Course } from '../../models/course.model';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css'
})
export class StudentProfileComponent implements OnInit, OnDestroy {
  enrolledCourses$: Observable<Course[]>;
  enrolledCourses: Course[] = [];
  totalCredits = 0;
  private subscription: Subscription | null = null;

  constructor(private store: Store) {
    this.enrolledCourses$ = this.store.select(selectEnrolledCourses);
  }

  ngOnInit(): void {
    this.subscription = this.enrolledCourses$.subscribe(courses => {
      this.enrolledCourses = courses || [];
      this.totalCredits = this.calculateTotalCredits(this.enrolledCourses);
    });
  }

  calculateTotalCredits(courses: Course[]): number {
    return courses.reduce((total, course) => total + course.credits, 0);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}