import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';

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
  isLoading = true;
  isEnrolled = false;
  private subscription: Subscription | null = null;
  private enrollSubscription: Subscription | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService,
    private enrollmentService: EnrollmentService
  ) {}

  ngOnInit(): void {
    // Read the :id parameter from the route
    this.courseId = this.route.snapshot.paramMap.get('id');
    
    if (this.courseId) {
      const id = parseInt(this.courseId, 10);
      this.loadCourse(id);
    }
  }

  loadCourse(id: number): void {
    this.isLoading = true;
    this.subscription = this.courseService.getCourseById(id).subscribe({
      next: (course) => {
        this.course = course;
        this.isLoading = false;
        this.checkEnrollmentStatus();
      },
      error: (err) => {
        console.error('Error loading course:', err);
        this.isLoading = false;
        this.router.navigate(['/not-found']);
      }
    });
  }

  checkEnrollmentStatus(): void {
    if (this.course) {
      this.isEnrolled = this.enrollmentService.isEnrolled(this.course.id);
    }
  }

  toggleEnrollment(): void {
    if (this.course) {
      if (this.isEnrolled) {
        this.unenrollFromCourse();
      } else {
        this.enrollInCourse();
      }
    }
  }

  enrollInCourse(): void {
    if (this.course) {
      this.enrollSubscription = this.enrollmentService.enroll(this.course.id).subscribe({
        next: () => {
          this.isEnrolled = true;
          console.log('Enrolled in course:', this.course?.id);
        },
        error: (err) => {
          console.error('Enrollment failed:', err);
          alert('Failed to enroll. Please try again.');
        }
      });
    }
  }

  unenrollFromCourse(): void {
    if (this.course) {
      this.enrollSubscription = this.enrollmentService.unenroll(this.course.id).subscribe({
        next: () => {
          this.isEnrolled = false;
          console.log('Unenrolled from course:', this.course?.id);
        },
        error: (err) => {
          console.error('Unenrollment failed:', err);
          alert('Failed to unenroll. Please try again.');
        }
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/courses']);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    if (this.enrollSubscription) {
      this.enrollSubscription.unsubscribe();
    }
  }
}